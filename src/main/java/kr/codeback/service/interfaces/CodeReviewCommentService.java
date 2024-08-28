package kr.codeback.service.interfaces;

import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.Member;

public interface CodeReviewCommentService {

	void deleteByMember(Member member);

	void deleteByCodeReview(CodeReview codeReview);

}
