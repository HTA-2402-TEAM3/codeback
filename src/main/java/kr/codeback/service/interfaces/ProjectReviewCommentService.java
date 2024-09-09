package kr.codeback.service.interfaces;

import kr.codeback.model.dto.request.review.CodeReviewCommentRequestDTO;
import kr.codeback.model.entity.CodeReviewComment;

public interface ProjectReviewCommentService {
    CodeReviewComment saveComment(CodeReviewCommentRequestDTO commentDTO);
}
