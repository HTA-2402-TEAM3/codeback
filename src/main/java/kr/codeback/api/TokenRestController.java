package kr.codeback.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.codeback.model.dto.request.TokenRequestDTO;
import kr.codeback.model.dto.request.UserRequestDTO;
import kr.codeback.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class TokenRestController {

	private final JwtUtil jwtUtil;

	@PostMapping("/")
	public String generateAccessToken(@RequestBody TokenRequestDTO tokenRequestDTO){
		return jwtUtil.generateAccessToken(tokenRequestDTO.getEmail(), tokenRequestDTO.getNickname());
	}

	// @PostMapping("/")
	// public String generateRefreshToken(@RequestBody TokenRequestDTO tokenRequestDTO){
	// 	return jwtUtil.generateRefreshToken(tokenRequestDTO.getEmail());
	// }

}
