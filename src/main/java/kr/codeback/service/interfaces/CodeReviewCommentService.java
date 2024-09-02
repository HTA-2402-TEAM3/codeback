package kr.codeback.service.interfaces;

import kr.codeback.model.dto.request.review.CodeReviewCommentRequestDTO;
import kr.codeback.model.dto.response.review.CodeReviewCommentResponseDTO;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;

import java.util.UUID;

public interface CodeReviewCommentService {

	void deleteByMember(Member member);

	void deleteByCodeReview(CodeReview codeReview);

    CodeReviewCommentResponseDTO saveComment(CodeReviewCommentRequestDTO commentDTO);

	void deleteById(UUID codeReviewCommentId);
}
