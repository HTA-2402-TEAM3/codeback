package kr.codeback.service.impl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.exception.ErrorCode;
import kr.codeback.exception.review.ReviewNonExistentException;
import kr.codeback.exception.review.ReviewNotAuthorizedException;
import kr.codeback.model.dto.request.review.ProjectReviewModifyRequestDTO;
import kr.codeback.model.dto.request.review.ProjectReviewRequestDTO;
import kr.codeback.model.dto.response.review.ProjectReviewListResponseDTO;
import kr.codeback.model.dto.response.summary.SummaryByMonthResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewImage;
import kr.codeback.model.entity.ProjectReviewTag;
import kr.codeback.repository.ProjectReviewRepository;
import kr.codeback.repository.specification.ProjectReviewSpecification;
import kr.codeback.service.interfaces.PreferenceService;
import kr.codeback.service.interfaces.ProjectReviewCommentService;
import kr.codeback.service.interfaces.ProjectReviewImageService;
import kr.codeback.service.interfaces.ProjectReviewService;
import kr.codeback.service.interfaces.ProjectReviewTagService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectReviewServiceImpl implements ProjectReviewService {

	private final ProjectReviewRepository projectReviewRepository;

	private final ProjectReviewCommentService projectReviewCommentService;
	private final ProjectReviewImageService projectReviewImageService;
	private final ProjectReviewTagService projectReviewTagService;
	private final PreferenceService preferenceService;

	public Page<ProjectReviewListResponseDTO> findAllWithPage(int pageNum, int pageSize, String sort) {
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, sort));

		return projectReviewRepository.findAll(pageable).map((ProjectReview projectReview) -> {
			String thumbnailUrl = projectReview.getProjectReviewImages()
				.stream()
				.findFirst()
				.map(ProjectReviewImage::getUrl)
				.orElse(null);

			return ProjectReviewListResponseDTO.builder()
				.id(projectReview.getId())
				.member(projectReview.getMember().getNickname())
				.createDate(projectReview.getCreateDate())
				.title(projectReview.getTitle())
				.projectReviewTags(projectReview.getProjectReviewTags()
					.stream()
					.map(ProjectReviewTag::getTag)
					.collect(Collectors.toList()))
				.projectReviewThumbnails(thumbnailUrl) // null일 수 있음
				.preferenceCnt(preferenceService.findByEntityID(projectReview.getId()).size())
				.projectReviewComments(projectReview.getComments().size())
				.build();
		});
	}

	@Override
	public ProjectReview findById(UUID projectID) {
		return projectReviewRepository.findById(projectID)
			.orElseThrow(() -> new ReviewNonExistentException(ErrorCode.NONEXISTENT_REVIEW.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()));
	}

	@Override
	@Transactional
	public ProjectReview save(Member member, ProjectReviewRequestDTO pjRequestDTO) throws IOException {
		ProjectReview reviewObj = ProjectReview.builder()
			.id(UUID.randomUUID())
			.member(member)
			.title(pjRequestDTO.getTitle())
			.content(pjRequestDTO.getContent())
			.githubURL(pjRequestDTO.getGithubUrl())
			.build();
		projectReviewRepository.save(reviewObj);
		Set<ProjectReviewTag> tagSet = new LinkedHashSet<>();
		Set<ProjectReviewImage> imageSet = new LinkedHashSet<>();

		if (pjRequestDTO.getTags() != null) {
			tagSet = projectReviewTagService.save(pjRequestDTO.getTags(), reviewObj);
		}
		if (pjRequestDTO.getImageFiles() != null) {
			imageSet = projectReviewImageService.save(pjRequestDTO.getImageFiles(), reviewObj);
		}

		reviewObj.addSet(imageSet, tagSet);
		return projectReviewRepository.save(reviewObj);
	}

	@Override
	@Transactional
	public void deleteAllById(UUID projectID) {
		projectReviewImageService.deleteAllByProjectReviewId(projectID);
		projectReviewTagService.deleteAllByProjectReviewId(projectID);
		projectReviewCommentService.deleteAllByProjectReviewId(projectID);
		projectReviewRepository.deleteById(projectID);
	}

	@Override
	@Transactional
	public void updateProjectReview(UUID reviewId, ProjectReviewModifyRequestDTO projectDTO) throws
		NullPointerException {
		ProjectReview projectReview = projectReviewRepository.findById(reviewId)
			.orElseThrow(() -> new ReviewNonExistentException(ErrorCode.NONEXISTENT_REVIEW.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()));

		if (!projectDTO.getMemberEmail().equals(projectReview.getMember().getEmail())) {
			throw new ReviewNotAuthorizedException(ErrorCode.NOT_EXIST_USER.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage());
		}

		if (projectDTO.getImageFiles() != null && projectDTO.getTags() != null) {
			ProjectReview updateImageProjectReview = projectReviewImageService.updateImages(projectReview,
				projectDTO.getFileNames(), projectDTO.getImageFiles());
			ProjectReview updateTagProjectReview = projectReviewTagService.updateTags(updateImageProjectReview,
				projectDTO.getTags());
			updateTagProjectReview.updateProjectReview(projectDTO);
			projectReviewRepository.save(updateTagProjectReview);
		} else {
			projectReview.updateProjectReview(projectDTO);
			projectReviewRepository.save(projectReview);
		}
	}

	@Override
	public Page<ProjectReviewListResponseDTO> findWithFilters(String search, boolean isTag, int pageNum, int pageSize,
		String sort) {

		Specification<ProjectReview> specification;
		if (isTag) {
			specification = Specification.where(ProjectReviewSpecification.hasTag(search));
		} else {
			specification = Specification.where(ProjectReviewSpecification.hasKeyword(search));
		}

		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, sort));

		return projectReviewRepository.findAll(specification, pageable).map((ProjectReview projectReview) -> {
			String thumbnailUrl = projectReview.getProjectReviewImages()
				.stream()
				.findFirst()
				.map(ProjectReviewImage::getUrl)
				.orElse(null);

			return ProjectReviewListResponseDTO.builder()
				.id(projectReview.getId())
				.member(projectReview.getMember().getNickname())
				.createDate(projectReview.getCreateDate())
				.title(projectReview.getTitle())
				.projectReviewTags(projectReview.getProjectReviewTags()
					.stream()
					.map(ProjectReviewTag::getTag)
					.collect(Collectors.toList()))
				.projectReviewThumbnails(thumbnailUrl) // null일 수 있음
				.preferenceCnt(preferenceService.findByEntityID(projectReview.getId()).size())
				.projectReviewComments(projectReview.getComments().size())
				.build();
		});
	}

	@Override
	public List<SummaryByMonthResponseDTO> calculateSummaryByMonth(String inputDate) {

		Date searchDate;
		if (inputDate == null || inputDate.isEmpty()) {
			searchDate = Date.valueOf(LocalDate.now());
		} else {
			searchDate = Date.valueOf(LocalDate.parse(inputDate));
		}

		List<Object[]> results = projectReviewRepository.calculateSummaryByMonth(searchDate);

		return results.stream()
			.map(row -> new SummaryByMonthResponseDTO(
				Integer.parseInt(row[0].toString()),
				((Number)row[1]).longValue()
			))
			.toList();

	}
}
