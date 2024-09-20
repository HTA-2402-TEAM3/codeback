package kr.codeback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletResponse;
import kr.codeback.model.dto.response.member.UserResponseDTO;
import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.Member;
import kr.codeback.service.interfaces.AuthorityService;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.util.CookieUtil;
import kr.codeback.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

	private final MemberService memberService;
	private final AuthorityService authorityService;
	private final JwtUtil jwtUtil;

	@GetMapping("/submit")
	public String submit() {
		return "view/submit";
	}

	@GetMapping("/registration")
	public String registration() {
		return "view/registration";
	}

	@GetMapping("/form/login")
	public String login() {
		return "user";
	}

	@PostMapping("/api/submit")
	public ResponseEntity<?> submit(@RequestBody UserResponseDTO userResponseDTO,
		HttpServletResponse response) {

		//token 생성
		String token = jwtUtil.generateAccessToken(userResponseDTO.getEmail(), userResponseDTO.getNickname(),userResponseDTO.getRole());

		Authority authority = authorityService.findByName("ROLE_USER");

		Member member = Member.builder()
			.authority(authority)
			.email(userResponseDTO.getEmail())
			.nickname(userResponseDTO.getNickname())
			.build();

		memberService.save(member);

		CookieUtil.createCookie(response, "jwtToken", token, 1800000);

		return ResponseEntity.ok(userResponseDTO);
	}

}
