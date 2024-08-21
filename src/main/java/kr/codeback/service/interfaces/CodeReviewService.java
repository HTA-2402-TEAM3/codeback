package kr.codeback.service.interfaces;

import java.util.ArrayList;
import java.util.Optional;

import kr.codeback.model.entity.CodeReview;

public interface CodeReviewService {

	// 모든 게시물 조회
	ArrayList<CodeReview> findCodeReviewAll();

	// ID로 게시물 조회
	Optional<CodeReview> findCodeReviewById(String id);

	// 작성자 이름으로 게시물 조회
	ArrayList<CodeReview> findCodeReviewByAuthor(String author);

	// 제목으로 게시물 조회
	ArrayList<CodeReview> findCodeReviewByTitle(String title);

	// 게시물 삭제 (JpaRepository의 delete() 메서드 제공)
	Boolean deleteCodeReviewById(String id);
}
