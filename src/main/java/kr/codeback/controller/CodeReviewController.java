package kr.codeback.controller;

import java.util.List;
import java.util.UUID;

import kr.codeback.model.dto.DTOSample;
import kr.codeback.model.entity.CodeLanguageCategory;
import kr.codeback.service.ServiceSample;
import kr.codeback.service.interfaces.CodeLanguageCategoryService;
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

	@GetMapping("/{id}")
	public String checkDetail(@PathVariable(name = "id") String inputID, Model model) {

		UUID id = UUID.fromString(inputID);

		CodeReview codeReview = codeReviewService.findById(id);

		model.addAttribute("codeReview", CodeReviewResponseDTO.builder()
			.id(codeReview.getId())
			.member(codeReview.getMember())
			.title(codeReview.getTitle())
			.content(codeReview.getContent())
			.createDate(codeReview.getCreateDate())
			.codeLanguageName(codeReview.getCodeLanguageCategory().getLanguageName())
			.codeReviewComments(codeReview.getComments())
			.build());

		return "view/view-code";
	}

	@GetMapping("/list")
	public String checkReviews(@RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
							   @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
							   @RequestParam(required = false, defaultValue = "createDate", value = "sort") String sort,
							   Model model) {

		List<CodeReviewResponseDTO> reviews = codeReviewService.findCodeReviewAll(pageNum, pageSize, sort);
		List<CodeLanguageCategory> languages = codeLanguageCategoryService.findAll();


		model.addAttribute("languages", languages);
		model.addAttribute("reviews", reviews);

		return "/view/review-list";
	}
}
