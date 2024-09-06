package kr.codeback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.codeback.repository.NotificationRepository;
import kr.codeback.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NotificationController {

	@GetMapping("/notice")
	public String notification() {
		return "/view/notification/notification"; // Thymeleaf 템플릿 이름
	}

}