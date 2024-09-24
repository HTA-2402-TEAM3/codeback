package kr.codeback.service.interfaces;

import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProjectReviewImageService {
    Set<ProjectReviewImage> save(List<MultipartFile> imageFiles, ProjectReview reviewObj) throws IOException;

    void deleteAllByProjectReviewId(UUID projectReviewId);

    List<String> getFilenames(Set<ProjectReviewImage> projectReviewImages);

    Set<ProjectReviewImage> findAllByProjectReviewId(UUID projectReviewId);

    ProjectReview updateImages(ProjectReview review, List<String> imageUrls, List<MultipartFile> imageFiles);
}
