package kr.codeback.service.interfaces;

import java.util.List;

import kr.codeback.model.dto.response.summary.CodeReviewCommentSummaryResponseDTO;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.Member;

public interface CodeReviewCommentService {

	void deleteByMember(Member member);

	void deleteByCodeReview(CodeReview codeReview);

	List<CodeReviewCommentSummaryResponseDTO> calculateSummaryByMonth(String inputDate);

}
