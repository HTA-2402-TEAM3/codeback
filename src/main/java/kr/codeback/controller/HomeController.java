package kr.codeback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String homePage(){
		log.info("homecontroller는 거쳐갔습니다.");
		return "index";
	}
}
