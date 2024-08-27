package kr.codeback.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.CodeReviewRepository;
import kr.codeback.service.interfaces.CodeReviewCommentService;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import kr.codeback.service.interfaces.CodeReviewService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeReviewServiceImpl implements CodeReviewService {

	private final CodeReviewRepository codeReviewRepository;

	private final CodeReviewCommentService codeReviewCommentService;
	private final CodeReviewPreferenceService codeReviewPreferenceService;

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
	public void deleteByMember(Member member) {

		List<CodeReview> deleteCodeReviews = findByMember(member);

		deleteCodeReviews.forEach(codeReviewCommentService::deleteByCodeReview);
		deleteCodeReviews.forEach(codeReview -> codeReviewPreferenceService.deleteByEntityID(codeReview.getId()));

		codeReviewRepository.deleteAll(deleteCodeReviews);
	}

	@Override
	public List<CodeReview> findByMember(Member member) {
		return codeReviewRepository.findByMemberEmail(member.getEmail());
	}
}
