package kr.codeback.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.exception.ErrorCode;
import kr.codeback.exception.member.MemberNotFoundException;
import kr.codeback.exception.review.ReviewNonExistentException;
import kr.codeback.exception.review.ReviewNotAuthorizedException;
import kr.codeback.model.dto.request.review.CodeReviewCommentRequestDTO;
import kr.codeback.model.dto.request.review.CommentModifyRequestDTO;
import kr.codeback.model.dto.response.summary.SummaryByMonthResponseDTO;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Preference;
import kr.codeback.repository.CodeReviewCommentRepository;
import kr.codeback.repository.CodeReviewRepository;
import kr.codeback.repository.MemberRepository;
import kr.codeback.service.interfaces.CodeReviewCommentService;
import kr.codeback.service.interfaces.NotificationService;
import kr.codeback.service.interfaces.PreferenceService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeReviewCommentServiceImpl implements CodeReviewCommentService {

	private final CodeReviewCommentRepository codeReviewCommentRepository;

	private final PreferenceService preferenceService;
	private final NotificationService notificationService;
	private final MemberRepository memberRepository;
	private final CodeReviewRepository codeReviewRepository;

	@Override
	@Transactional
	public void deleteByMember(Member member) {

		List<CodeReviewComment> codeReviewComments = codeReviewCommentRepository.findByMember(member);

		if (codeReviewComments.isEmpty()) {
			return;
		}

		codeReviewComments.stream()
			.map(CodeReviewComment::getId)
			.forEach(notificationService::deleteByEntityId);

		codeReviewComments.stream()
			.map(CodeReviewComment::getId)
			.forEach(preferenceService::deleteByEntityID);

		codeReviewCommentRepository.deleteAll(codeReviewComments);
	}

	@Override
	@Transactional
	public void deleteByCodeReview(CodeReview codeReview) {

		List<CodeReviewComment> codeReviewComments = codeReview.getComments();

		codeReviewComments.stream()
			.map(CodeReviewComment::getId)
			.forEach(notificationService::deleteByEntityId);

		codeReviewComments.stream()
			.map(CodeReviewComment::getId)
			.forEach(preferenceService::deleteByEntityID);

		codeReviewCommentRepository.deleteAll(codeReviewComments);
	}

	@Override
	public CodeReviewComment saveComment(CodeReviewCommentRequestDTO commentDTO) {
		Member member = memberRepository.findByEmail(commentDTO.getMemberEmail())
			.orElseThrow(() -> new MemberNotFoundException(
				ErrorCode.NOT_EXIST_USER.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()
			));

		CodeReview codeReview = codeReviewRepository.findById(commentDTO.getReviewId())
			.orElseThrow(() -> new ReviewNonExistentException(
				ErrorCode.NONEXISTENT_REVIEW.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()
			));

		CodeReviewComment codeReviewComment = CodeReviewComment.builder()
			.comment(commentDTO.getContent())
			.id(UUID.randomUUID())
			.member(member)
			.codeReview(codeReview)
			.build();
		codeReviewCommentRepository.save(codeReviewComment);

		return codeReviewComment;
	}

	@Override
	public void deleteById(UUID commentId, String memberEmail) {
		CodeReviewComment comment = codeReviewCommentRepository.findById(commentId)
			.orElseThrow(() -> new ReviewNonExistentException(
				ErrorCode.NONEXISTENT_REVIEW.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()
			));

		if (!comment.getMember().getEmail().equals(memberEmail)) {
			throw new ReviewNotAuthorizedException(
				ErrorCode.NOT_EXIST_USER.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()
			);
		}

		List<Preference> preferences = preferenceService.findByEntityID(commentId);
		preferenceService.deleteAll(preferences);

		notificationService.deleteByEntityId(commentId);

		codeReviewCommentRepository.delete(comment);
	}

	@Override
	public void update(CommentModifyRequestDTO commentDTO) {
		CodeReviewComment comment = codeReviewCommentRepository.findById(commentDTO.getId())
			.orElseThrow(() -> new ReviewNonExistentException(
				ErrorCode.NOT_EXIST_USER.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()
			));

		if (!commentDTO.getMemberEmail().equals(comment.getMember().getEmail())) {
			throw new ReviewNotAuthorizedException(
				ErrorCode.NOT_EXIST_USER.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()
			);
		}

		comment.updateCodeReviewComment(commentDTO);
		codeReviewCommentRepository.save(comment);
	}

	@Override
	public List<SummaryByMonthResponseDTO> calculateSummaryByMonth(String inputDate) {

		Date searchDate;
		if (inputDate == null || inputDate.isEmpty()) {
			searchDate = Date.valueOf(LocalDate.now());
		} else {
			searchDate = Date.valueOf(LocalDate.parse(inputDate));
		}

		List<Object[]> results = codeReviewCommentRepository.calculateSummaryByMonth(searchDate);

		return results.stream().map(
				row -> new SummaryByMonthResponseDTO(
					Integer.parseInt(row[0].toString()),
					((Number)row[1]).longValue()
				))
			.toList();
	}
}
