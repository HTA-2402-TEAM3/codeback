package kr.codeback.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.exception.ErrorCode;
import kr.codeback.exception.review.CommentNonExistentException;
import kr.codeback.exception.review.ReviewNotAuthorizedException;
import kr.codeback.model.dto.request.review.CommentModifyRequestDTO;
import kr.codeback.model.dto.request.review.ProjectReviewCommentRequestDTO;
import kr.codeback.model.dto.response.summary.SummaryByMonthResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Preference;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewComment;
import kr.codeback.repository.ProjectReviewCommentRepository;
import kr.codeback.service.interfaces.NotificationService;
import kr.codeback.service.interfaces.PreferenceService;
import kr.codeback.service.interfaces.ProjectReviewCommentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectReviewCommentServiceImpl implements ProjectReviewCommentService {

	private final ProjectReviewCommentRepository projectReviewCommentRepository;
	private final PreferenceService preferenceService;
	private final NotificationService notificationService;

	@Override
	@Transactional
	public void deleteAllByProjectReviewId(UUID projectReviewId) {
		projectReviewCommentRepository.deleteAllByProjectReviewId(projectReviewId);
	}

	@Override
	@Transactional
	public ProjectReviewComment saveComment(ProjectReviewCommentRequestDTO commentDTO, Member member,
		ProjectReview projectReview) {
		ProjectReviewComment projectReviewComment = ProjectReviewComment.builder()
			.id(UUID.randomUUID())
			.content(commentDTO.getContent())
			.projectReview(projectReview)
			.member(member)
			.build();
		return projectReviewCommentRepository.save(projectReviewComment);
	}

	@Override
	public void deleteById(UUID id, String memberEmail) {
		ProjectReviewComment comment = projectReviewCommentRepository.findById(id)
			.orElseThrow(() -> new CommentNonExistentException(
				ErrorCode.NONEXISTENT_COMMENT.getStatus(),
				ErrorCode.NONEXISTENT_REVIEW.getMessage()
			));

		if (!comment.getMember().getEmail().equals(memberEmail)) {
			throw new ReviewNotAuthorizedException(
				ErrorCode.NOT_EXIST_USER.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()
			);
		}

		List<Preference> preferenceList = preferenceService.findByEntityID(id);
		preferenceService.deleteAll(preferenceList);

		//		List<Notification> notifications = notificationService.findByEntityID(id);
		notificationService.deleteByEntityId(id);

		projectReviewCommentRepository.delete(comment);
	}

	@Override
	public void update(CommentModifyRequestDTO commentDTO) {
		ProjectReviewComment comment = projectReviewCommentRepository.findById(commentDTO.getId())
			.orElseThrow(() -> new CommentNonExistentException(
				ErrorCode.NONEXISTENT_COMMENT.getStatus(),
				ErrorCode.NONEXISTENT_REVIEW.getMessage())
			);

		if (!commentDTO.getMemberEmail().equals(comment.getMember().getEmail())) {
			throw new ReviewNotAuthorizedException(
				ErrorCode.NOT_EXIST_USER.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()
			);
		}

		comment.updateProjectReviewComment(commentDTO);
		projectReviewCommentRepository.save(comment);
	}

	@Override
	public List<SummaryByMonthResponseDTO> calculateSummaryByMonth(String inputDate) {

		Date searchDate;
		if (inputDate == null || inputDate.isEmpty()) {
			searchDate = Date.valueOf(LocalDate.now());
		} else {
			searchDate = Date.valueOf(LocalDate.parse(inputDate));
		}

		List<Object[]> results = projectReviewCommentRepository.calculateSummaryByMonth(searchDate);

		return results.stream()
			.map(row -> new SummaryByMonthResponseDTO(
				Integer.parseInt(row[0].toString()),
				((Number)row[1]).longValue()
			))
			.toList();
	}
}
