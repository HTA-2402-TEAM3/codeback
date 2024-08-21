package kr.codeback.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.codeback.model.entity.Board;
import kr.codeback.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	@Override
	public ArrayList<Board> findBoardAll() {
		return null;
	}

	@Override
	public Optional<Board> findBoardById(String id) {
		return Optional.empty();
	}

	@Override
	public ArrayList<Board> findBoardByAuthor(String author) {
		return null;
	}

	@Override
	public ArrayList<Board> findBoardByTitle(String title) {
		return null;
	}

	@Override
	public Boolean deleteBoardById(String id) {
		return null;
	}
}
