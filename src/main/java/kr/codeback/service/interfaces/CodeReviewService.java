package kr.codeback.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import kr.codeback.model.dto.request.review.CodeReviewRequestDTO;
import kr.codeback.model.dto.response.summary.CodeReviewSummaryByLanguageResponseDTO;
import kr.codeback.model.dto.response.summary.CodeReviewSummaryByMonthResponseDTO;
import kr.codeback.model.dto.response.review.CodeReviewListResponseDTO;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.Member;

public interface CodeReviewService {

    // 모든 게시물 조회
    Page<CodeReviewListResponseDTO> findAllWithPage(int pageNum, int pageSize, String sort);

    // 언어 태그 별 게시물 조쇠
    //Page<CodeReviewListResponseDTO> findCodeReviewByLanguage(UUID language, int pageNum, int pageSize, String sort);

    // ID로 게시물 조회
    CodeReview findById(UUID id);

    void deleteByMember(Member member);

    List<CodeReview> findByMember(Member member);

    void deleteCodeReviewById(UUID id);

    // 게시물 작성
    void saveCodeReview(CodeReviewRequestDTO codeReviewRequestDTO);

    void updateCodeReview(CodeReviewRequestDTO reviewDTO);

    Page<CodeReviewListResponseDTO> findWithFilters(String search, UUID language, int pageNum, int pageSize, String sort);
	List<CodeReviewSummaryByLanguageResponseDTO> calculateSummaryByLanguage();

	List<CodeReviewSummaryByMonthResponseDTO> calculateSummaryByMonth(String inputDate);

}
