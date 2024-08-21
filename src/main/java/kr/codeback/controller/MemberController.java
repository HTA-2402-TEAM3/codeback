package kr.codeback.controller;

import org.springframework.stereotype.Controller;

import kr.codeback.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberServiceImpl memberService;
}
