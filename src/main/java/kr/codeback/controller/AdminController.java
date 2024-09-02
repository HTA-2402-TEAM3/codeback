package kr.codeback.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.codeback.model.dto.response.summary.CodeReviewCommentSummaryResponseDTO;
import kr.codeback.model.dto.response.summary.CodeReviewPreferenceSummaryResponseDTO;
import kr.codeback.model.dto.response.summary.CodeReviewSummaryByLanguageResponseDTO;
import kr.codeback.model.dto.response.summary.CodeReviewSummaryByMonthResponseDTO;
import kr.codeback.model.dto.response.MemberSummaryResponseDTO;
import kr.codeback.service.interfaces.CodeReviewCommentService;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import kr.codeback.service.interfaces.CodeReviewService;
import kr.codeback.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final MemberService memberService;
	private final CodeReviewService codeReviewService;
	private final CodeReviewCommentService codeReviewCommentService;
	private final CodeReviewPreferenceService codeReviewPreferenceService;

	@GetMapping("/admin/members")
	public String moveMembers(Model model) {

		model.addAttribute("initialMembers", new ArrayList<>());

		return "/view/admin/members";
	}

	@GetMapping("/admin/summary")
	public String moveSummary(Model model) {

		MemberSummaryResponseDTO memberSummaryResponseDTO = memberService.getMemberSummary();
		List<CodeReviewSummaryByLanguageResponseDTO> codeReviewSummaryResponseDTOS = codeReviewService.calculateSummaryByLanguage();
		List<CodeReviewSummaryByMonthResponseDTO> codeReviewSummaryByMonthResponseDTOS = codeReviewService.calculateSummaryByMonth();
		List<CodeReviewCommentSummaryResponseDTO> codeReviewCommentSummaryResponseDTOS = codeReviewCommentService.calculateSummaryByMonth();
		List<CodeReviewPreferenceSummaryResponseDTO> codeReviewPreferenceSummaryResponseDTOS = codeReviewPreferenceService.calculateSummaryByMonth();

		System.out.println(codeReviewSummaryByMonthResponseDTOS);

		model.addAttribute("memberSummary", memberSummaryResponseDTO);
		model.addAttribute("codeReviewSummaryByLanguage", codeReviewSummaryResponseDTOS);
		model.addAttribute("codeReviewSummaryByMonth", codeReviewSummaryByMonthResponseDTOS);
		model.addAttribute("codeReviewCommentSummaryByMonth", codeReviewCommentSummaryResponseDTOS);
		model.addAttribute("codeReviewPreferenceSummaryByMonth", codeReviewPreferenceSummaryResponseDTOS);

		return "/view/admin/summary";

	}

}
