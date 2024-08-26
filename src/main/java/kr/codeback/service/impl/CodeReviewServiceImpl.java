package kr.codeback.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.repository.CodeReviewRepository;
import kr.codeback.service.interfaces.CodeReviewCommentService;
import kr.codeback.service.interfaces.CodeReviewService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeReviewServiceImpl implements CodeReviewService {

	private final CodeReviewRepository codeReviewRepository;

	private final CodeReviewCommentService codeReviewCommentService;

	@Override
	public ArrayList<CodeReview> findCodeReviewAll() {
		return null;
	}

	@Override
	public CodeReview findById(UUID id) {

		Optional<CodeReview> optionalCodeReview = codeReviewRepository.findById(id);

		return optionalCodeReview.orElseThrow(
			() -> new IllegalArgumentException("No code review found with id: " + id)
		);

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

	@Override
	@Transactional
	public void deleteAllByEmail(String deleteEmail) {

		codeReviewCommentService.deleteAllByEmail(deleteEmail);
		codeReviewRepository.deleteAllByEmail(deleteEmail);
	}
}
