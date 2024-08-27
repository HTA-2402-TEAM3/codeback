package kr.codeback.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.repository.CodeReviewCommentRepository;
import kr.codeback.service.interfaces.CodeReviewCommentService;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import kr.codeback.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeReviewCommentServiceImpl implements CodeReviewCommentService {

	private final CodeReviewCommentRepository codeReviewCommentRepository;

	private final CodeReviewPreferenceService codeReviewPreferenceService;
	private final NotificationService notificationService;

	@Override
	@Transactional
	public void deleteByEmail(String deleteEmail) {
		codeReviewCommentRepository.deleteByEmail(deleteEmail);
	}

	@Override
	public List<CodeReviewComment> findByCodeReview(CodeReview codeReview) {
		return codeReviewCommentRepository.findByCodeReview(codeReview);
	}

	@Override
	@Transactional
	public void deleteByCodeReview(CodeReview codeReview) {

		List<CodeReviewComment> codeReviewComments = findByCodeReview(codeReview);

		codeReviewComments.stream()
			.map(CodeReviewComment::getId)
			.forEach(notificationService::deleteByEntityID);

		codeReviewComments.stream()
			.map(CodeReviewComment::getId)
			.forEach(codeReviewPreferenceService::deleteByEntityID);

		codeReviewCommentRepository.deleteAll(codeReviewComments);
	}

}
