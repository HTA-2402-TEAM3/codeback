package kr.codeback.service.interfaces;

import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProjectReviewImageService {
    Set<ProjectReviewImage> save(List<MultipartFile> imageFiles, ProjectReview reviewObj) throws IOException;
}
