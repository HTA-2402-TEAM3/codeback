package kr.codeback.service.interfaces;

import kr.codeback.model.dto.request.review.CodeReviewCommentRequestDTO;
import kr.codeback.model.dto.request.review.CommentModifyRequestDTO;
import kr.codeback.model.dto.request.review.ProjectReviewCommentRequestDTO;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewComment;

import java.util.UUID;

public interface ProjectReviewCommentService {

	void deleteAllByProjectReviewId(UUID projectReviewId);

	ProjectReviewComment saveComment(ProjectReviewCommentRequestDTO commentDTO, Member member, ProjectReview projectReview);

	void deleteById(UUID id, String memberEmail);

	void update(CommentModifyRequestDTO commentDTO);
}
