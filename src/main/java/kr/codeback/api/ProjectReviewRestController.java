package kr.codeback.api;

import kr.codeback.common.MessageResponseDTO;
import kr.codeback.model.constant.SuccessMessage;
import kr.codeback.model.dto.request.review.ProjectReviewRequestDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewImage;
import kr.codeback.service.impl.ProjectReviewServiceImpl;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.interfaces.ProjectReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectReviewRestController {
    private final ProjectReviewService projectReviewService;
    private final MemberService memberService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveProjectReview(@ModelAttribute ProjectReviewRequestDTO projectReviewRequestDTO) throws Exception {
        Member member = memberService.findByEmail(projectReviewRequestDTO.getMemberEmail());
        ProjectReview projectReview = projectReviewService.save(member, projectReviewRequestDTO);
        return ResponseEntity.ok().body(new MessageResponseDTO(projectReview+ SuccessMessage.CREATE.getMessage()));
    }

}
