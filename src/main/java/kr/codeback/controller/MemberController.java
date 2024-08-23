package kr.codeback.controller;

import java.util.UUID;

import jakarta.servlet.http.HttpServletResponse;
import kr.codeback.util.CookieUtil;
import kr.codeback.util.JwtUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.Member;
import kr.codeback.service.impl.AuthorityServiceImpl;
import kr.codeback.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberServiceImpl memberService;
	private final AuthorityServiceImpl authorityService;
	private final JwtUtil jwtUtil;


	@GetMapping("/home")
	public String homepage(@RequestParam String email, @RequestParam String nickname) {

		System.out.println(memberService.findByEmail(email).get());

		return "/index";
	}

	@GetMapping("/test")
	public String home() {
		return "/view/test"; // home.html을 반환
	}

	@GetMapping("/user")
	public String user(@AuthenticationPrincipal OAuth2User principal, Model model) {
		// GitHub 사용자 정보 처리
		model.addAttribute("username", principal.getAttribute("login")); // GitHub username
		model.addAttribute("email", principal.getAttribute("email")); // GitHub email
		return "user"; // user.html를 반환
	}


	@PostMapping("/submit")
	public String submit(@RequestParam("email") String email,
						 @RequestParam("nickname") String nickname,
						 HttpServletResponse response) {

		//token 생성
		String token = jwtUtil.generateToken(email);

		//uuid 생성
		UUID uuid = UUID.randomUUID();

		Authority authority = Authority.builder()
				.id(uuid)
				.name("member")
				.build();

		Member member = Member.builder()
				.authority(authority)
				.email(email)
				.nickname(nickname)
				.build();

		memberService.save(member);

		CookieUtil.createCookie(response,"jwtToken",token,1800);

		return "/login";
	}



}
