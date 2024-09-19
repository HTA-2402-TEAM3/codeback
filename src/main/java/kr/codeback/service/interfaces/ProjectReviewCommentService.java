package kr.codeback.service.interfaces;

import java.util.List;
import java.util.UUID;

import kr.codeback.model.dto.request.review.CommentModifyRequestDTO;
import kr.codeback.model.dto.request.review.ProjectReviewCommentRequestDTO;
import kr.codeback.model.dto.response.summary.SummaryByMonthResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewComment;

public interface ProjectReviewCommentService {

	void deleteAllByProjectReviewId(UUID projectReviewId);

	ProjectReviewComment saveComment(ProjectReviewCommentRequestDTO commentDTO, Member member,
		ProjectReview projectReview);

	void deleteById(UUID id, String memberEmail);

	void update(CommentModifyRequestDTO commentDTO);

	List<SummaryByMonthResponseDTO> calculateSummaryByMonth(String inputDate);

}
