package kr.codeback.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.codeback.model.entity.CodeReview;
import kr.codeback.service.interfaces.CodeReviewService;

@Service
public class CodeReviewServiceImpl implements CodeReviewService {
	@Override
	public ArrayList<CodeReview> findCodeReviewAll() {
		return null;
	}

	@Override
	public Optional<CodeReview> findCodeReviewById(String id) {
		return Optional.empty();
	}

	@Override
	public ArrayList<CodeReview> findCodeReviewByAuthor(String author) {
		return null;
	}

	@Override
	public ArrayList<CodeReview> findCodeReviewByTitle(String title) {
		return null;
	}

	@Override
	public Boolean deleteCodeReviewById(String id) {
		return null;
	}
}
