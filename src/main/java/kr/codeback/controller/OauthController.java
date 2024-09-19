package kr.codeback.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/oauth")
@RequiredArgsConstructor
@Controller
public class OauthController {

	@GetMapping()
	public String oauthLogin() {
		return "view/oauth";
	}

	@GetMapping("/github/callback")
	public Map<String, String> githubCallback(@RequestParam Map<String, String> codemap) {

		return null;
	}

	@GetMapping("/google/callback")
	public Map<String, String> googleCallback(@RequestParam Map<String, String> codemap) {

		return null;
	}

}
