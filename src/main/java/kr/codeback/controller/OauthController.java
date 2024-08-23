package kr.codeback.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.codeback.util.HttpUtil;
import lombok.RequiredArgsConstructor;

@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {

	private final HttpUtil httpUtil;

	@GetMapping("/github/callback")
	public Map<String,String> githubCallback(@RequestParam Map<String,String> codemap){

		return null;
	}

}
