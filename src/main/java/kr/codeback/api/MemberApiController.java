package kr.codeback.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.codeback.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberApiController {

	private final JwtUtil jwtUtil;

	@GetMapping("/user/email")
	public ResponseEntity<Map<String, String>> getUserEmail(
		@CookieValue(value = "jwtToken", required = false) String jwtToken) {
		Map<String, String> response = new HashMap<>();

		if (jwtToken != null) {
			String email = jwtUtil.extractEmail(jwtToken);
			response.put("email", email);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Unauthorized"));
		}
	}
}