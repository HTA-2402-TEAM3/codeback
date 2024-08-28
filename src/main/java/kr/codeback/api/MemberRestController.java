package kr.codeback.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import kr.codeback.model.dto.request.TokenRequestDTO;
import kr.codeback.model.dto.request.UserRequestDTO;
import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.Member;
import kr.codeback.service.interfaces.AuthorityService;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.member.EmailSignUpService;
import kr.codeback.util.CookieUtil;
import kr.codeback.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberRestController {

	private final JwtUtil jwtUtil;
	private final MemberService memberService;
	private final AuthorityService authorityService;
	private final EmailSignUpService emailSignUpService;

	//유저 정보 가져오기
	@GetMapping("/info")
	public ResponseEntity<Map<String, String>> getUserEmail(
		@CookieValue(value = "access_token", required = false) String jwtToken) {
		Map<String, String> response = new HashMap<>();

		if (jwtToken != null) {
			String email = jwtUtil.extractEmail(jwtToken);
			response.put("email", email);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(Collections.singletonMap("error", "Unauthorized"));
		}
	}

	// 클라이언트 이메일로 링크 발송
	@PostMapping("/email")
	public ResponseEntity<String> emailSender(@RequestBody TokenRequestDTO tokenRequestDTO) {
		String jwtToken = jwtUtil.generateRegistrationToken(tokenRequestDTO.getEmail());
		emailSignUpService.sendVerificationEmail(tokenRequestDTO.getEmail(), jwtToken);
		return ResponseEntity.ok(jwtToken);
	}

	// 자사 로그인 (클라이언트 이메일에서 링크 누르면 여기로 검증)
	@GetMapping("/registration")
	public ResponseEntity<UserRequestDTO> registration(@RequestParam("code") String code) {

		//토큰 검증 및 회원가입
		if (jwtUtil.validateToken(code)) {

			String email = jwtUtil.extractEmail(code);
			//권한 생성
			Authority authority = authorityService.findByName("ROLE_USER");

			//멤버 생성
			Member member = Member.builder()
				.id(UUID.randomUUID())
				.authority(authority)
				.email(email)
				.nickname(emailSignUpService.substringEmail(email))
				.build();

			memberService.save(member);

			UserRequestDTO userRequestDTO = UserRequestDTO.builder()
				.email(email)
				.nickname(emailSignUpService.substringEmail(email))
				.build();
			//멤버 저장
			return ResponseEntity.ok(userRequestDTO);
		} else {
			return ResponseEntity.ok(null);
		}
	}

	//토큰 및 쿠키 생성
	@PostMapping("/signin")
	public ResponseEntity<String> signIn(@RequestBody UserRequestDTO userRequestDTO, HttpServletResponse response) {

		String accessToken = jwtUtil.generateAccessToken(userRequestDTO.getEmail(), userRequestDTO.getNickname());
		String refreshToken = jwtUtil.generateRefreshToken(userRequestDTO.getEmail());

		CookieUtil.createCookie(response, "access_token", accessToken, 60 * 60 * 60 * 10);
		CookieUtil.createCookie(response, "refresh_token", refreshToken, 60 * 60 * 60 * 24 * 14);

		return ResponseEntity.ok("로그인 성공");
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse response) {
		CookieUtil.deleteCookie(response, "access_token");
		CookieUtil.deleteCookie(response, "refresh_token");
		return ResponseEntity.ok("로그아웃 되었습니다.");
	}

}