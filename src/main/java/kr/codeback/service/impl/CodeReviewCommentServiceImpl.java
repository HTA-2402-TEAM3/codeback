package kr.codeback.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.dto.response.summary.CodeReviewCommentSummaryResponseDTO;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;
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
	public void deleteByMember(Member member) {

		List<CodeReviewComment> codeReviewComments = codeReviewCommentRepository.findByMember(member);

		if (codeReviewComments.isEmpty()) {
			return;
		}

		codeReviewComments.stream()
			.map(CodeReviewComment::getId)
			.forEach(notificationService::deleteByEntityID);

		codeReviewComments.stream()
			.map(CodeReviewComment::getId)
			.forEach(codeReviewPreferenceService::deleteByEntityID);

		codeReviewCommentRepository.deleteAll(codeReviewComments);
	}

	@Override
	@Transactional
	public void deleteByCodeReview(CodeReview codeReview) {

		List<CodeReviewComment> codeReviewComments = codeReview.getComments();

		codeReviewComments.stream()
			.map(CodeReviewComment::getId)
			.forEach(notificationService::deleteByEntityID);

		codeReviewComments.stream()
			.map(CodeReviewComment::getId)
			.forEach(codeReviewPreferenceService::deleteByEntityID);

		codeReviewCommentRepository.deleteAll(codeReviewComments);
	}

	@Override
	public List<CodeReviewCommentSummaryResponseDTO> calculateSummaryByMonth(String inputDate) {

		Date searchDate = null;
		if (inputDate == null || inputDate.isEmpty()) {
			searchDate = Date.valueOf(LocalDate.now());
		} else {
			searchDate = Date.valueOf(LocalDate.parse(inputDate));
		}

		List<Object[]> results = codeReviewCommentRepository.calculateSummaryByMonth(searchDate);

		return results.stream().map(
				row -> new CodeReviewCommentSummaryResponseDTO(
					Integer.parseInt(row[0].toString()),
					((Number)row[1]).longValue()
				))
			.toList();

	}
}
