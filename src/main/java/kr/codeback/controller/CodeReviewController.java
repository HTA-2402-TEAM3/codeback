package kr.codeback.controller;

import java.util.List;
import java.util.UUID;

import kr.codeback.model.dto.response.review.CodeReviewListResponseDTO;
import kr.codeback.model.entity.CodeLanguageCategory;
import kr.codeback.model.entity.CodeReviewPreference;
import kr.codeback.service.interfaces.CodeLanguageCategoryService;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.codeback.model.dto.response.review.CodeReviewResponseDTO;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.service.interfaces.CodeReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class CodeReviewController {

    private final CodeReviewService codeReviewService;
    private final CodeLanguageCategoryService codeLanguageCategoryService;
    private final CodeReviewPreferenceService codeReviewPreferenceService;

    @GetMapping("/{id}")
    public String checkDetail(@PathVariable(name = "id") String inputID, Model model) {

        UUID id = UUID.fromString(inputID);

        CodeReview codeReview = codeReviewService.findById(id);
        List<CodeReviewPreference> codeReviewPreference = codeReviewPreferenceService.findById(id);

        model.addAttribute("codeReview", CodeReviewResponseDTO.builder()
                .id(codeReview.getId())
                .member(codeReview.getMember())
                .title(codeReview.getTitle())
                .content(codeReview.getContent())
                .createDate(codeReview.getCreateDate())
                .codeLanguageName(codeReview.getCodeLanguageCategory().getLanguageName())
                .codeReviewComments(codeReview.getComments())
                .preferenceCnt(codeReviewPreference.size())
                .build());

        return "view/codeReview/view-code";
    }

    @GetMapping("/")
    public String checkReviews(Model model) {
        Page<CodeReviewListResponseDTO> page = codeReviewService.findAllWithPage(0, 10, "createDate");
        List<CodeReviewListResponseDTO> reviews = page.getContent();
        List<CodeLanguageCategory> languages = codeLanguageCategoryService.findAll();

        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("languages", languages);
        model.addAttribute("reviews", reviews);

        return "/view/codeReview/review-list";
    }

    @GetMapping("/write")
    public String writeReview(@RequestParam(value = "id", required = false)UUID id, Model model) {
        List<CodeLanguageCategory> languageCategories = codeLanguageCategoryService.findAll();

        model.addAttribute("languages", languageCategories);
        return "/view/codeReview/write";
    }

}
