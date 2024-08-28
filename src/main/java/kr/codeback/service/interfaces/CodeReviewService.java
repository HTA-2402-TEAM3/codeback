package kr.codeback.service.interfaces;

import java.util.List;
import java.util.UUID;

import kr.codeback.model.dto.request.CodeReviewRequestDTO;
import kr.codeback.model.dto.response.review.CodeReviewListResponseDTO;
import kr.codeback.model.entity.CodeReview;
import org.springframework.data.domain.Page;

public interface CodeReviewService {

	// 모든 게시물 조회
	Page<CodeReviewListResponseDTO> findCodeReviewAll(int pageNum, int pageSize, String sort);

	// 언어 태그 별 게시물 조쇠
	Page<CodeReviewListResponseDTO> findCodeReviewByLanguage(UUID language, int pageNum, int pageSize, String sort);

	// ID로 게시물 조회
	CodeReview findById(UUID id);

	// 작성자 이름으로 게시물 조회
	List<CodeReview> findCodeReviewByAuthor(String author);

	// 제목으로 게시물 조회
	List<CodeReview> findCodeReviewByTitle(String title);

	// 게시물 삭제 (JpaRepository의 delete() 메서드 제공)
	Boolean deleteCodeReviewById(String id);

	// 게시물 작성
	CodeReview saveCodeReview(CodeReviewRequestDTO codeReviewRequestDTO);
}
