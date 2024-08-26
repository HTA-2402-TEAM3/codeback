package kr.codeback.controller;

import java.util.List;
import java.util.UUID;

import kr.codeback.model.dto.DTOSample;
import kr.codeback.model.entity.CodeLanguageCategory;
import kr.codeback.model.entity.CodeReviewPreference;
import kr.codeback.service.ServiceSample;
import kr.codeback.service.interfaces.CodeLanguageCategoryService;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.codeback.model.dto.response.CodeReviewResponseDTO;
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
                .member(codeReview.getMember().getNickname())
                .title(codeReview.getTitle())
                .content(codeReview.getContent())
                .createDate(codeReview.getCreateDate())
                .codeLanguageName(codeReview.getCodeLanguageCategory().getLanguageName())
                .codeReviewComments(codeReview.getComments().size())
                .preferenceCnt(codeReviewPreference.size())
                .build());

        return "view/view-code";
    }

    @GetMapping("/")
    public String checkReviews(Model model) {
        List<CodeReviewResponseDTO> reviews = codeReviewService.findCodeReviewAll(0, 10, "createDate").getContent();
        List<CodeLanguageCategory> languages = codeLanguageCategoryService.findAll();

        model.addAttribute("totalPages", codeReviewService.findCodeReviewAll(0, 10, "createDate").getTotalPages());
        model.addAttribute("languages", languages);
        model.addAttribute("reviews", reviews);

        return "/view/review-list";
    }
}
