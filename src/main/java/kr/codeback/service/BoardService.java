package kr.codeback.service;

import java.util.ArrayList;
import java.util.Optional;

import kr.codeback.model.entity.Board;

public interface BoardService {

	// 모든 게시물 조회
	ArrayList<Board> findBoardAll();

	// ID로 게시물 조회
	Optional<Board> findBoardById(String id);

	// 작성자 이름으로 게시물 조회
	ArrayList<Board> findBoardByAuthor(String author);

	// 제목으로 게시물 조회
	ArrayList<Board> findBoardByTitle(String title);

	// 게시물 삭제 (JpaRepository의 delete() 메서드 제공)
	Boolean deleteBoardById(String id);
}
