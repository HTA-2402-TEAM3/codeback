package kr.codeback.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.codeback.model.dto.DTOSample;
import kr.codeback.service.ServiceSample;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/sample")
@RequiredArgsConstructor
public class ControllerSample {

	private final ServiceSample serviceSample;

	@GetMapping("/reviews")
	public String checkReviews(@RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
		@RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
		@RequestParam(required = false, defaultValue = "createDate", value = "sort") String sort,
		Model model) {

		List<DTOSample> dtoSamples = serviceSample.findCodeReviewAll(pageNum, pageSize, sort);

		model.addAttribute("dtoSamples", dtoSamples);

		return "/view/sample-reviews";
	}

}
