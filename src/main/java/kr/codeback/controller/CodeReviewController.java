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

import kr.codeback.model.dto.response.review.CodeReviewCommentResponseDTO;
import kr.codeback.model.dto.response.review.CodeReviewListResponseDTO;
import kr.codeback.model.dto.response.review.CodeReviewResponseDTO;
import kr.codeback.model.entity.CodeLanguageCategory;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.service.interfaces.CodeLanguageCategoryService;
import kr.codeback.service.interfaces.CodeReviewService;
import kr.codeback.service.interfaces.PreferenceService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class CodeReviewController {
    private final CodeReviewService codeReviewService;
    private final CodeLanguageCategoryService codeLanguageCategoryService;
    private final PreferenceService preferenceService;

    @GetMapping("/{id}")
    public String checkDetail(@PathVariable(name = "id") String inputID, Model model) {

        UUID id = UUID.fromString(inputID);

        CodeReview codeReview = codeReviewService.findById(id);
        int preferenceCnt = preferenceService.getCount(codeReview.getId());

        List<CodeReviewComment> codeReviewComments = codeReview.getComments();
        Map<UUID, Long> preferenceCounts = preferenceService.countByEntityIDs(
                codeReviewComments.stream()
                        .map(CodeReviewComment::getId)
                        .toList());

        List<CodeReviewCommentResponseDTO> codeReviewCommentResponseDTOs = codeReviewComments.stream()
                .map(comment -> CodeReviewCommentResponseDTO.builder()
                        .id(comment.getId())
                        .email(comment.getMember().getEmail())
                        .nickname(comment.getMember().getNickname())
                        .commentContent(comment.getComment())
                        .createDate(comment.getCreateDate())
                        .preferenceCnt(preferenceCounts.getOrDefault(comment.getId(), 0L))
                        .build()
                ).toList();

        model.addAttribute("codeReview", CodeReviewResponseDTO.builder()
                .id(codeReview.getId())
                .nickname(codeReview.getMember().getNickname())
                .email(codeReview.getMember().getEmail())
                .title(codeReview.getTitle())
                .content(codeReview.getContent())
                .createDate(codeReview.getCreateDate())
                .codeLanguageName(codeReview.getCodeLanguageCategory().getLanguageName())
                .codeReviewComments(codeReviewCommentResponseDTOs)
                .preferenceCnt(preferenceCnt)
                .build());

        return "view/codeReview/view-code";
    }

    @GetMapping("/")
    public String checkReviews(Model model) {
        Page<CodeReviewListResponseDTO> page = codeReviewService.findAllWithPage(0, 10, "createDate");
        List<CodeReviewListResponseDTO> reviews = page.getContent();

        Map<UUID, Long> preferenceCnt = preferenceService.countByEntityIDs(
                reviews.stream().map(CodeReviewListResponseDTO::getId)
                        .toList());
        reviews.forEach(review -> review.setPreferenceCnt(preferenceCnt.getOrDefault(review.getId(), 0L)));

        List<CodeLanguageCategory> languages = codeLanguageCategoryService.findAll();

        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("languages", languages);
        model.addAttribute("reviews", reviews);

        return "view/codeReview/review-list";
    }

    @GetMapping("/write")
    public String writeReview(@RequestParam(value = "id", required = false) UUID id, Model model) {
        List<CodeLanguageCategory> languageCategories = codeLanguageCategoryService.findAll();

        model.addAttribute("languages", languageCategories);
        return "view/codeReview/write";
    }
}
