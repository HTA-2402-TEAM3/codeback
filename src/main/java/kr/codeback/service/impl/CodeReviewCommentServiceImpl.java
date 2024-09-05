package kr.codeback.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import kr.codeback.exception.ErrorCode;
import kr.codeback.exception.review.ReviewNotAuthorizedException;
import kr.codeback.model.dto.request.review.CodeReviewCommentRequestDTO;
import kr.codeback.model.dto.request.review.CommentModifyRequestDTO;
import kr.codeback.model.dto.response.review.CodeReviewCommentResponseDTO;
import kr.codeback.model.entity.*;
import kr.codeback.repository.CodeReviewRepository;
import kr.codeback.repository.MemberRepository;
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
	public CodeReviewCommentResponseDTO saveComment(CodeReviewCommentRequestDTO commentDTO) {
		Member member = memberRepository.findByEmail(commentDTO.getMemberEmail())
				.orElseThrow(()-> new IllegalArgumentException("no member..."));
		CodeReview codeReview = codeReviewRepository.findById(commentDTO.getCodeReviewId())
				.orElseThrow(()-> new IllegalArgumentException("no codeReview..."));

		CodeReviewComment codeReviewComment = CodeReviewComment.builder()
				.comment(commentDTO.getContent())
				.id(UUID.randomUUID())
				.member(member)
				.codeReview(codeReview)
				.build();
		codeReviewCommentRepository.save(codeReviewComment);

		CodeReviewComment savedComment = codeReviewCommentRepository.findById(codeReviewComment.getId())
				.orElseThrow(()->new IllegalArgumentException("no comment..."+codeReviewComment.getId()));

		return savedComment.toDTO();
	}

	@Override
	public void deleteById(UUID commentId, String memberEmail) {
		CodeReviewComment comment = codeReviewCommentRepository.findById(commentId)
				.orElseThrow(()->new IllegalArgumentException("no comments.."+commentId));

		if(!comment.getMember().getEmail().equals(memberEmail)) {
			throw new ReviewNotAuthorizedException(
					ErrorCode.NOT_EXIST_USER.getStatus(),
					ErrorCode.NOT_EXIST_USER.getMessage()
			);
		}

		List<CodeReviewPreference> preferences = codeReviewPreferenceService.findByEntityID(commentId);
		codeReviewPreferenceService.deleteAll(preferences);

		List<Notification> notifications = notificationService.findByEntityID(commentId);
		notificationService.deleteAll(notifications);

		codeReviewCommentRepository.delete(comment);
	}

	@Override
	public void update(CommentModifyRequestDTO commentDTO) {
		CodeReviewComment comment = codeReviewCommentRepository.findById(commentDTO.getId())
				.orElseThrow(()->new IllegalArgumentException("no Comment..."));

		if(!commentDTO.getMemberEmail().equals(comment.getMember().getEmail())) {
			throw new ReviewNotAuthorizedException(
					ErrorCode.NOT_EXIST_USER.getStatus(),
					ErrorCode.NOT_EXIST_USER.getMessage()
			);
		}

		comment.updateCodeReviewComment(commentDTO);
		codeReviewCommentRepository.save(comment);
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
