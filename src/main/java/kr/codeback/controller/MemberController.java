package kr.codeback.controller;

import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.codeback.model.dto.response.UserResponseDTO;
import kr.codeback.service.interfaces.AuthorityService;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.util.CookieUtil;
import kr.codeback.util.JwtUtil;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.Member;
import lombok.RequiredArgsConstructor;

import static kr.codeback.util.CookieUtil.getCookieValue;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final AuthorityService authorityService;
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

	//수정이 필요합니다...
	@GetMapping("/user/test")
	public String readCookie(Model model,HttpServletRequest request) {
		String cookieValue = getCookieValue(request, "jwtToken");
		model.addAttribute("username", "test setting"); // GitHub username
		model.addAttribute("email", ("email")); // GitHub email
		return "user";
	}


	@GetMapping("/submit")
	public String subget(){
		return "/view/submit";
	}



	@GetMapping("/form/login")
	public String login(){
		return "/user";
	}

	@PostMapping("/api/submit")
	public ResponseEntity<?> submit(@RequestBody UserResponseDTO userResponseDTO,
						 HttpServletResponse response) {


		//token 생성
		String token = jwtUtil.generateAccessToken(userResponseDTO.getEmail(),userResponseDTO.getNickname());

		Authority authority = authorityService.findByName("ROLE_USER").get();

		Member member = Member.builder()
				.authority(authority)
				.email(userResponseDTO.getEmail())
				.nickname(userResponseDTO.getNickname())
				.build();

		memberService.save(member);

		CookieUtil.createCookie(response,"jwtToken",token,1800);

		return ResponseEntity.ok(userResponseDTO);
	}

}
