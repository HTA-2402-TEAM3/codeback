package kr.codeback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectReviewController {
    @GetMapping("/write")
    public String writeProjectReview(@RequestParam(value = "id", required = false) UUID id) {
        return "/view/projectReview/project-write";
    }

    @GetMapping("/")
    public String projectReview() {
        return "/view/projectReview/project-list";
    }
    @GetMapping("/view")
    public String viewProjectReview() {
        return "/view/projectReview/project-view";
    }
}
