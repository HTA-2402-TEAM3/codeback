package kr.codeback.service.interfaces;

import kr.codeback.model.dto.response.review.ProjectReviewResponseDTO;
import kr.codeback.model.entity.ProjectReview;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface ProjectReviewService {
    ProjectReview findById(UUID projectID);
}
