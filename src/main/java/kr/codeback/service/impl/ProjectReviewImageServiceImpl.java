package kr.codeback.service.impl;

import jakarta.transaction.Transactional;
import kr.codeback.exception.ErrorCode;
import kr.codeback.exception.review.FailUploadedImageException;
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
            throw new FailUploadedImageException(
                    ErrorCode.FAIL_UPLOAD_IMAGE.getStatus(),
                    ErrorCode.NOT_EXIST_USER.getMessage()
            );
        }
        return imageSet;
    }

    @Override
    @Transactional
    public void deleteAllByProjectReviewId(UUID projectReviewId) {
        Set<ProjectReviewImage> images = findAllByProjectReviewId(projectReviewId);
        if(!images.isEmpty()) {
            List<String> filenames = getFilenames(images);
            s3Service.deleteS3Files(filenames);
        }
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
        List<ProjectReviewImage> deleteImages = new ArrayList<>();
        if(fileNames == null) {
            fileNames = new ArrayList<>();
        }
        if(!fileNames.isEmpty()) {
            // Iterator를 사용하여 안전하게 요소를 제거
            Iterator<ProjectReviewImage> iterator = imageSet.iterator();
            while (iterator.hasNext()) {
                ProjectReviewImage projectReviewImage = iterator.next();
                if (fileNames.contains(projectReviewImage.getFileName())) {
                    deleteImages.add(projectReviewImage);
                    iterator.remove(); // 안전하게 제거
                    s3Service.delete(projectReviewImage.getFileName());
                }
            }
            // 저장된 images와 fileName 비교 후 연관성 삭제
            review.deleteProjectReviewImages(deleteImages);
        }
        return addProjectReviewImages(imageFiles, review);
    }


    private ProjectReview addProjectReviewImages(List<MultipartFile> imageFiles, ProjectReview review) {
        if(imageFiles == null) {
            imageFiles = new ArrayList<>();
        }
        if(!imageFiles.isEmpty()) {
//        s3 저장 후 projectReview 에 연관성 추가
            List<ProjectReviewImage> imageSet = new ArrayList<>();
            List<String> fileNames = new ArrayList<>();
            try {
                for (MultipartFile imageFile : imageFiles) {
                    String fileName = UUID.randomUUID() + imageFile.getOriginalFilename();
                    String url = s3Service.upload(imageFile, fileName);
                    fileNames.add(fileName);

                    ProjectReviewImage image = ProjectReviewImage.builder()
                            .projectReview(review)
                            .id(UUID.randomUUID())
                            .fileName(fileName)
                            .url(url)
                            .build();

                    imageSet.add(image);
                }
            } catch (IOException e) {
                for (String fileName : fileNames) {
                    s3Service.delete(fileName);
                }
                throw new FailUploadedImageException(
                        ErrorCode.FAIL_UPLOAD_IMAGE.getStatus(),
                        ErrorCode.NOT_EXIST_USER.getMessage()
                );
            }
            review.addProjectReviewImages(imageSet);
//        연관성 추가
        }
        return review;
    }
}
