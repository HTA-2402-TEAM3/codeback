package kr.codeback.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.codeback.model.dto.response.MemberSummaryResponseDTO;
import kr.codeback.model.dto.response.summary.CodeReviewSummaryByLanguageResponseDTO;
import kr.codeback.model.dto.response.summary.SummaryByMonthResponseDTO;
import kr.codeback.service.interfaces.CodeReviewCommentService;
import kr.codeback.service.interfaces.CodeReviewService;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.interfaces.PreferenceService;
import kr.codeback.service.interfaces.ProjectReviewCommentService;
import kr.codeback.service.interfaces.ProjectReviewService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final MemberService memberService;
	private final CodeReviewService codeReviewService;
	private final CodeReviewCommentService codeReviewCommentService;
	private final PreferenceService preferenceService;
	private final ProjectReviewService projectReviewService;
	private final ProjectReviewCommentService projectReviewCommentService;

	@GetMapping("/admin/members")
	public String moveMembers(Model model) {

		model.addAttribute("initialMembers", new ArrayList<>());

		return "view/admin/members";
	}

	@GetMapping("/admin/summary")
	public String moveSummary(Model model, @RequestParam(required = false) String searchDate) {

		MemberSummaryResponseDTO memberSummaryResponseDTO = memberService.getMemberSummary();
		List<CodeReviewSummaryByLanguageResponseDTO> codeReviewSummaryResponseDTOS = codeReviewService.calculateSummaryByLanguage();
		List<SummaryByMonthResponseDTO> codeReviewSummaryByMonthResponseDTOS = codeReviewService.calculateSummaryByMonth(
			searchDate);
		List<SummaryByMonthResponseDTO> summaryByMonthResponseDTOS = codeReviewCommentService.calculateSummaryByMonth(
			searchDate);
		List<SummaryByMonthResponseDTO> codeReviewPreferenceSummaryResponseDTOS = preferenceService.calculateSummaryByMonth(
			searchDate);
		List<SummaryByMonthResponseDTO> projectReviewSummaryResponseDTOS = projectReviewService.calculateSummaryByMonth(
			searchDate);
		List<SummaryByMonthResponseDTO> projectReviewCommentSummaryResponseDTOS = projectReviewCommentService.calculateSummaryByMonth(
			searchDate);

		model.addAttribute("memberSummary", memberSummaryResponseDTO);
		model.addAttribute("codeReviewSummaryByLanguage", codeReviewSummaryResponseDTOS);
		model.addAttribute("codeReviewSummaryByMonth", codeReviewSummaryByMonthResponseDTOS);
		model.addAttribute("codeReviewCommentSummaryByMonth", summaryByMonthResponseDTOS);
		model.addAttribute("codeReviewPreferenceSummaryByMonth", codeReviewPreferenceSummaryResponseDTOS);
		model.addAttribute("projectReviewSummaryByMonth", projectReviewSummaryResponseDTOS);
		model.addAttribute("projectReviewCommentSummaryByMonth", projectReviewCommentSummaryResponseDTOS);

		return "view/admin/summary";

	}

}
