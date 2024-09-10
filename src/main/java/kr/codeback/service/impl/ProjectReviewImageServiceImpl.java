package kr.codeback.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewImage;
import kr.codeback.repository.ProjectReviewImageRepository;
import kr.codeback.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import kr.codeback.service.interfaces.ProjectReviewImageService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectReviewImageServiceImpl implements ProjectReviewImageService {
    private final S3Service s3Service;
    private final ProjectReviewImageRepository projectReviewImageRepository;

    @Override
    @Transactional
    public Set<ProjectReviewImage> save(List<MultipartFile> imageFiles, ProjectReview reviewObj) {
        Set<ProjectReviewImage> imageSet = new LinkedHashSet<>();
        List<String> fileNames = new ArrayList<>();
        try {
            for (MultipartFile imageFile : imageFiles) {
                String fileName = UUID.randomUUID() + imageFile.getOriginalFilename();
                String url = s3Service.upload(imageFile, fileName);
                fileNames.add(fileName);

                ProjectReviewImage image = ProjectReviewImage.builder()
                        .projectReview(reviewObj)
                        .id(UUID.randomUUID())
                        .fileName(fileName)
                        .url(url)
                        .build();

                imageSet.add(image);
            }
            projectReviewImageRepository.saveAll(imageSet);
        }catch (IOException e) {
            for(String fileName : fileNames) {
                s3Service.delete(fileName);
            }
            throw new RuntimeException("fail to upload Image...");
        }
        return imageSet;
    }

    @Override
    @Transactional
    public void deleteAllByProjectReviewId(UUID projectReviewId) {
        Set<ProjectReviewImage> images = findAllByProjectReviewId(projectReviewId);
        List<String> filenames = getFilenames(images);
        s3Service.deleteS3Files(filenames);
    }

    @Override
    public List<String> getFilenames(Set<ProjectReviewImage> projectReviewImages) {

        List<String> filenames = new ArrayList<>();

        for (ProjectReviewImage projectReviewImage : projectReviewImages) {
            filenames.add(projectReviewImage.getFileName());
        }

        return filenames;
    }

    @Override
    public Set<ProjectReviewImage> findAllByProjectReviewId(UUID projectReviewId) {
        return projectReviewImageRepository.findAllByProjectReviewId(projectReviewId);
    }

    @Override
    public ProjectReview updateImages(ProjectReview review, List<String> fileNames, List<MultipartFile> imageFiles) {
        Set<ProjectReviewImage> imageSet = projectReviewImageRepository.findAllByProjectReviewId(review.getId());
//        기존 저장되어 있던 images

        List<ProjectReviewImage> deleteImages = new ArrayList<>();
        for (ProjectReviewImage projectReviewImage : imageSet) {
            if (fileNames.contains(projectReviewImage.getFileName())) {
                deleteImages.add(projectReviewImage);
                imageSet.remove(projectReviewImage);
                s3Service.delete(projectReviewImage.getFileName());
            }
        }
//        저장된 images 와 fileName 비교해 같은 것 s3에서 삭제 후 db 삭제 용 list 에 image 객체 추가

        review.deleteProjectReviewImages(deleteImages);
//        연관성 삭제
        List<ProjectReviewImage> addImages = addProjectReviewImages(imageFiles);

        review.addProjectReviewImages(addImages);
        return review;
    }


}
