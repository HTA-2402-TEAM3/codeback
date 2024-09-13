package kr.codeback.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.codeback.model.dto.response.MemberSummaryResponseDTO;
import kr.codeback.model.dto.response.summary.CodeReviewCommentSummaryResponseDTO;
import kr.codeback.model.dto.response.summary.CodeReviewPreferenceSummaryResponseDTO;
import kr.codeback.model.dto.response.summary.CodeReviewSummaryByLanguageResponseDTO;
import kr.codeback.model.dto.response.summary.CodeReviewSummaryByMonthResponseDTO;
import kr.codeback.service.interfaces.CodeReviewCommentService;
import kr.codeback.service.interfaces.PreferenceService;
import kr.codeback.service.interfaces.CodeReviewService;
import kr.codeback.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final MemberService memberService;
	private final CodeReviewService codeReviewService;
	private final CodeReviewCommentService codeReviewCommentService;
	private final PreferenceService preferenceService;

	@GetMapping("/admin/members")
	public String moveMembers(Model model) {

		model.addAttribute("initialMembers", new ArrayList<>());

		return "view/admin/members";
	}

	@GetMapping("/admin/summary")
	public String moveSummary(Model model, @RequestParam(required = false) String searchDate) {

		MemberSummaryResponseDTO memberSummaryResponseDTO = memberService.getMemberSummary();
		List<CodeReviewSummaryByLanguageResponseDTO> codeReviewSummaryResponseDTOS = codeReviewService.calculateSummaryByLanguage();
		List<CodeReviewSummaryByMonthResponseDTO> codeReviewSummaryByMonthResponseDTOS = codeReviewService.calculateSummaryByMonth(
			searchDate);
		List<CodeReviewCommentSummaryResponseDTO> codeReviewCommentSummaryResponseDTOS = codeReviewCommentService.calculateSummaryByMonth(
			searchDate);
		List<CodeReviewPreferenceSummaryResponseDTO> codeReviewPreferenceSummaryResponseDTOS = preferenceService.calculateSummaryByMonth(
			searchDate);

		model.addAttribute("memberSummary", memberSummaryResponseDTO);
		model.addAttribute("codeReviewSummaryByLanguage", codeReviewSummaryResponseDTOS);
		model.addAttribute("codeReviewSummaryByMonth", codeReviewSummaryByMonthResponseDTOS);
		model.addAttribute("codeReviewCommentSummaryByMonth", codeReviewCommentSummaryResponseDTOS);
		model.addAttribute("codeReviewPreferenceSummaryByMonth", codeReviewPreferenceSummaryResponseDTOS);

		return "view/admin/summary";

	}

}
