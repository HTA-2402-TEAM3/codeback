package kr.codeback.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.codeback.model.dto.response.review.ProjectReviewCommentResponseDTO;
import kr.codeback.model.dto.response.review.ProjectReviewListResponseDTO;
import kr.codeback.model.dto.response.review.ProjectReviewResponseDTO;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewComment;
import kr.codeback.service.impl.ProjectReviewServiceImpl;
import kr.codeback.service.interfaces.PreferenceService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectReviewController {
	private final ProjectReviewServiceImpl projectReviewService;
	private final PreferenceService preferenceService;

	@GetMapping("/search")
	public String findWithTag(@RequestParam(value = "tag") String tag, Model model) {
		Page<ProjectReviewListResponseDTO> page = projectReviewService.findWithFilters(tag, true, 0, 9, "createDate");
		List<ProjectReviewListResponseDTO> reviews = page.getContent();

		model.addAttribute("findWithTag", tag);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("reviews", reviews);

		return "view/projectReview/project-list";
	}

	@GetMapping("/write")
	public String writeProjectReview(@RequestParam(value = "id", required = false) UUID id, Model model) {
		return "/view/projectReview/project-write";
	}

	@GetMapping("/")
	public String projectReview(Model model) {
		Page<ProjectReviewListResponseDTO> page = projectReviewService.findAllWithPage(0, 9, "createDate");
		List<ProjectReviewListResponseDTO> reviews = page.getContent();

		Map<UUID, Long> preferenceCnt = preferenceService.countByEntityIDs(
			reviews.stream().map(ProjectReviewListResponseDTO::getId)
				.toList());
		reviews.forEach(review -> review.setPreferenceCnt(preferenceCnt.getOrDefault(review.getId(), 0L)));

		model.addAttribute("findWithTag", null);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("reviews", reviews);

		return "view/projectReview/project-list";
	}

	@GetMapping("/{id}")
	public String viewProjectReview(@PathVariable(name = "id") UUID projectID, Model model) {
		ProjectReview projectReview = projectReviewService.findById(projectID);

		int preferenceCnt = preferenceService.getCount(projectReview.getId());

		List<ProjectReviewComment> projectReviewComments = projectReview.getComments();
		Map<UUID, Long> preferenceCounts = preferenceService.countByEntityIDs(
			projectReviewComments.stream()
				.map(ProjectReviewComment::getId)
				.toList());

		List<ProjectReviewCommentResponseDTO> codeReviewCommentResponseDTOs = projectReviewComments.stream()
			.map(comment -> ProjectReviewCommentResponseDTO.builder()
				.id(comment.getId())
				.email(comment.getMember().getEmail())
				.nickname(comment.getMember().getNickname())
				.commentContent(comment.getContent())
				.createDate(comment.getCreateDate())
				.preferenceCnt(preferenceCounts.getOrDefault(comment.getId(), 0L))
				.build()
			).toList();

		model.addAttribute("projectReview", ProjectReviewResponseDTO.builder()
			.id(projectReview.getId())
			.email(projectReview.getMember().getEmail())
			.nickname(projectReview.getMember().getNickname())
			.title(projectReview.getTitle())
			.content(projectReview.getContent())
			.createDate(projectReview.getCreateDate())
			.projectReviewTags(projectReview.getProjectReviewTags())
			.projectReviewComments(codeReviewCommentResponseDTOs)
			.projectReviewImages(projectReview.getProjectReviewImages())
			.preferenceCnt(preferenceCnt)
			.githubURL(projectReview.getGithubURL())

			.build());

		return "/view/projectReview/project-view";
	}
}
