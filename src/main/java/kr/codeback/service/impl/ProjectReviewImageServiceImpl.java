package kr.codeback.service.impl;

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
        List<ProjectReviewImage> images = findAllByProjectReviewId(projectReviewId);
        List<String> filenames = getFilenames(images);
        s3Service.deleteS3Files(filenames);
    }

    @Override
    public List<String> getFilenames(List<ProjectReviewImage> projectReviewImages) {

        List<String> filenames = new ArrayList<>();

        for (ProjectReviewImage projectReviewImage : projectReviewImages) {
            filenames.add(projectReviewImage.getFileName());
        }

        return filenames;
    }

    @Override
    public List<ProjectReviewImage> findAllByProjectReviewId(UUID projectReviewId) {
        return projectReviewImageRepository.findAllByProjectReviewId(projectReviewId);
    }

    @Override
    public Set<ProjectReviewImage> updateImages(List<MultipartFile> imageFiles) {
//        모르겟음...ㅠㅠㅠ
        return null;
    }

}
