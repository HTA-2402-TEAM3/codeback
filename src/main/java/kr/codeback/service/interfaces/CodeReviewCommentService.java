package kr.codeback.service.interfaces;

import java.util.List;
import java.util.UUID;

import kr.codeback.model.dto.request.review.CodeReviewCommentRequestDTO;
import kr.codeback.model.dto.request.review.CommentModifyRequestDTO;
import kr.codeback.model.dto.response.summary.SummaryByMonthResponseDTO;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;

public interface CodeReviewCommentService {

	void deleteByMember(Member member);

	void deleteByCodeReview(CodeReview codeReview);

	void deleteById(UUID codeReviewCommentId, String memberEmail);

	CodeReviewComment saveComment(CodeReviewCommentRequestDTO commentDTO);

	void update(CommentModifyRequestDTO commentDTO);

	List<SummaryByMonthResponseDTO> calculateSummaryByMonth(String inputDate);

}
