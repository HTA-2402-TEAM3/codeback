package kr.codeback.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.codeback.model.dto.response.CodeReviewSummaryByLanguageResponseDTO;
import kr.codeback.model.dto.response.CodeReviewSummaryByWeekResponseDTO;
import kr.codeback.model.dto.response.MemberSummaryResponseDTO;
import kr.codeback.service.interfaces.CodeReviewService;
import kr.codeback.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final MemberService memberService;
	private final CodeReviewService codeReviewService;

	@GetMapping("/admin/members")
	public String moveMembers(Model model) {

		model.addAttribute("initialMembers", new ArrayList<>());

		return "/view/admin/members";
	}

	@GetMapping("/admin/summary")
	public String moveSummary(Model model) {

		MemberSummaryResponseDTO memberSummaryResponseDTO = memberService.getMemberSummary();
		List<CodeReviewSummaryByLanguageResponseDTO> codeReviewSummaryResponseDTOs = codeReviewService.calculateSummaryByLanguage();
		List<CodeReviewSummaryByWeekResponseDTO> codeReviewSummaryByWeekResponseDTOs = codeReviewService.calculateSummaryByWeek();

		System.out.println(codeReviewSummaryByWeekResponseDTOs);

		model.addAttribute("memberSummary", memberSummaryResponseDTO);
		model.addAttribute("codeReviewSummaryByLanguage", codeReviewSummaryResponseDTOs);
		model.addAttribute("codeReviewSummaryByWeek", codeReviewSummaryByWeekResponseDTOs);

		return "/view/admin/summary";

	}

}
