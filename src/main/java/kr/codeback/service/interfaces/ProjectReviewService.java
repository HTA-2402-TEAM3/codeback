package kr.codeback.service.interfaces;

import kr.codeback.model.dto.request.review.ProjectReviewModifyRequestDTO;
import kr.codeback.model.dto.request.review.ProjectReviewRequestDTO;
import kr.codeback.model.dto.response.review.ProjectReviewListResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.ProjectReview;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.UUID;

public interface ProjectReviewService {
    ProjectReview findById(UUID projectID);

//    ProjectReview uploadFiles(List<MultipartFile> multipartFiles) throws IOException;

    ProjectReview save(Member member, ProjectReviewRequestDTO projectReviewRequestDTO) throws IOException;

    Page<ProjectReviewListResponseDTO> findAllWithPage(int pageNum, int pageSize, String sort);

    void deleteAllById(UUID projectID);

    void updateProjectReview(UUID reviewId, ProjectReviewModifyRequestDTO projectReviewRequestDTO) throws IOException;

    Page<ProjectReviewListResponseDTO> findWithFilters(String search, boolean isTag, int pageNum, int pageSize, String sort);
}
