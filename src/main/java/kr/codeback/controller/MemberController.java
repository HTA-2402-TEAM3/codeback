package kr.codeback.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.Member;
import kr.codeback.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberServiceImpl memberService;

	@GetMapping("/home")
	public String homepage(@RequestParam String email, @RequestParam String nickname) {

		Authority authority = Authority.builder()
			.id(UUID.randomUUID())
			.name("Manager")
			.build();

		Member member = Member.builder()
			.authority(authority)
			.email(email)
			.nickname(nickname)
			.build();

		memberService.saveMember(member);

		System.out.println(member.getEmail());
		return "/index";
	}
}
