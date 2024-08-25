package kr.codeback.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageMoveController {

	@GetMapping("/admin/members")
	public String moveMembers(Model model) {

		model.addAttribute("initialMembers", new ArrayList<>());

		return "/view/admin/members";
	}

}
