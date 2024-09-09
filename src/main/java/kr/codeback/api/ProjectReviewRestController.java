package kr.codeback.api;

import kr.codeback.common.MessageResponseDTO;
import kr.codeback.model.constant.SuccessMessage;
import kr.codeback.model.dto.request.review.ProjectReviewRequestDTO;
import kr.codeback.model.dto.response.review.CodeReviewListResponseDTO;
import kr.codeback.model.dto.response.review.CodeReviewPagingResponseDTO;
import kr.codeback.model.dto.response.review.ProjectReviewListResponseDTO;
import kr.codeback.model.dto.response.review.ProjectReviewPagingResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewImage;
import kr.codeback.service.impl.ProjectReviewServiceImpl;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.interfaces.ProjectReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/")
    public ResponseEntity<Object> allPages(@RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
                                           @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
                                           @RequestParam(required = false, defaultValue = "createDate", value = "sort") String sort) {
        Page<ProjectReviewListResponseDTO> page = projectReviewService.findAllWithPage(pageNum, pageSize, sort);

        ProjectReviewPagingResponseDTO reviews = ProjectReviewPagingResponseDTO.builder()
                .reviews(page.getContent())
                .totalPage(page.getTotalPages())
                .currentPage(pageNum)
                .build();

        return ResponseEntity.ok().body(reviews);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteProjectReview(@PathVariable UUID id){
        projectReviewService.deleteAllById(id);
        return ResponseEntity.ok(new MessageResponseDTO(projectReviewService.findById(id)+SuccessMessage.DELETE.getMessage()));
    }

}
