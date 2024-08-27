package kr.codeback.service.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.Member;

public interface CodeReviewService {

	// 모든 게시물 조회
	ArrayList<CodeReview> findCodeReviewAll();

	// ID로 게시물 조회
	CodeReview findById(UUID id);

	// 작성자 이름으로 게시물 조회
	ArrayList<CodeReview> findCodeReviewByAuthor(String author);

	// 제목으로 게시물 조회
	ArrayList<CodeReview> findCodeReviewByTitle(String title);

	// 게시물 삭제 (JpaRepository의 delete() 메서드 제공)
	Boolean deleteCodeReviewById(String id);

	void deleteByMember(Member member);

	List<CodeReview> findByMember(Member member);

}
