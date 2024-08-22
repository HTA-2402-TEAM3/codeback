package kr.codeback.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


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

		System.out.println(codeReview.getId());
		System.out.println(codeReview.getMember().getNickname());
		System.out.println(codeReview.getTitle());
		System.out.println(codeReview.getContent());
		System.out.println(codeReview.getCreateDate());
		System.out.println(codeReview.getCodeLanguageCategory().getLanguageName());



		model.addAttribute("codeReview", codeReview);

		return "review";
	}

}
