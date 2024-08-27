package kr.codeback.service.interfaces;

import java.util.List;

import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.CodeReviewComment;

public interface CodeReviewCommentService {

	// 이메일로 삭제
	void deleteByEmail(String deleteEmail);

	List<CodeReviewComment> findByCodeReview(CodeReview codeReview);

	void deleteByCodeReview(CodeReview codeReview);

}
