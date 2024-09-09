package kr.codeback.service.interfaces;

import kr.codeback.model.dto.request.review.ProjectReviewRequestDTO;
import kr.codeback.model.dto.response.review.ProjectReviewListResponseDTO;
import kr.codeback.model.dto.response.review.ProjectReviewResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.ProjectReview;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectReviewService {
    ProjectReview findById(UUID projectID);

//    ProjectReview uploadFiles(List<MultipartFile> multipartFiles) throws IOException;

    ProjectReview save(Member member, ProjectReviewRequestDTO projectReviewRequestDTO) throws IOException;

    Page<ProjectReviewListResponseDTO> findAllWithPage(int pageNum, int pageSize, String sort);
}
