package kr.codeback.controller;

import kr.codeback.model.dto.response.review.CodeReviewListResponseDTO;
import kr.codeback.model.dto.response.review.ProjectReviewListResponseDTO;
import kr.codeback.model.dto.response.review.ProjectReviewResponseDTO;
import kr.codeback.model.entity.CodeLanguageCategory;
import kr.codeback.model.entity.CodeReviewPreference;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.service.impl.ProjectReviewServiceImpl;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import kr.codeback.service.interfaces.ProjectReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectReviewController {
    private final ProjectReviewServiceImpl projectReviewService;
    private final CodeReviewPreferenceService preferenceService;

    @GetMapping("/write")
    public String writeProjectReview(@RequestParam(value = "id", required = false) UUID id) {
        return "/view/projectReview/project-write";
    }
    @GetMapping("/")
    public String projectReview(Model model) {
        Page<ProjectReviewListResponseDTO> page = projectReviewService.findAllWithPage(0,10,"createDate");
        List<ProjectReviewListResponseDTO> reviews = page.getContent();

        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("reviews", reviews);

        return "/view/projectReview/project-list";
    }
    @GetMapping("/{id}")
    public String viewProjectReview(@PathVariable(name = "id") UUID projectID, Model model) {
        ProjectReview projectReview = projectReviewService.findById(projectID);

        List<CodeReviewPreference> projectReviewPrefer = preferenceService.findByEntityID(projectID);

        model.addAttribute("projectReview", ProjectReviewResponseDTO.builder()
                        .id(projectReview.getId())
                        .member(projectReview.getMember())
                        .title(projectReview.getTitle())
                        .content(projectReview.getContent())
                        .createDate(projectReview.getCreateDate())
                        .projectReviewTags(projectReview.getProjectReviewTags())
                        .projectReviewComments(projectReview.getComments())
                        .projectReviewImages(projectReview.getProjectReviewImages())
                        .preferenceCnt(projectReviewPrefer.size())
                .build());

        return "/view/projectReview/project-view";
    }
}
