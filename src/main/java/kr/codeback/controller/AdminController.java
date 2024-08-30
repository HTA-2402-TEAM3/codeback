package kr.codeback.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.codeback.model.dto.response.MemberSummaryResponseDTO;
import kr.codeback.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final MemberService memberService;

	@GetMapping("/admin/members")
	public String moveMembers(Model model) {

		model.addAttribute("initialMembers", new ArrayList<>());

		return "/view/admin/members";
	}

	@GetMapping("/admin/summary")
	public String moveSummary(Model model) {

		MemberSummaryResponseDTO memberSummaryResponseDTO = memberService.getMemberSummary();

		System.out.println(memberSummaryResponseDTO);

		model.addAttribute("memberSummary", memberSummaryResponseDTO);

		return "/view/admin/summary";

	}

}
