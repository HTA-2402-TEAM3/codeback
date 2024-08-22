package kr.codeback.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.codeback.model.dto.response.CodeReviewResponseDTO;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.service.interfaces.CodeReviewService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class CodeReviewController {

	private final CodeReviewService codeReviewService;

	@GetMapping("/review/{id}")
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
			.build());

		return "view-code";
	}

}
