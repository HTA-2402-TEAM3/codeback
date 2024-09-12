package kr.codeback.api;

import kr.codeback.common.MessageResponseDTO;
import kr.codeback.model.constant.SuccessMessage;
import kr.codeback.model.dto.request.review.CommentModifyRequestDTO;
import kr.codeback.model.dto.request.review.ProjectReviewCommentRequestDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewComment;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.interfaces.NotificationService;
import kr.codeback.service.interfaces.ProjectReviewCommentService;
import kr.codeback.service.interfaces.ProjectReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/project/comment")
@RequiredArgsConstructor
public class ProjectReviewCommentRestController {
    private final ProjectReviewCommentService projectReviewCommentService;
    private final MemberService memberService;
    private final ProjectReviewService projectReviewService;
    private final NotificationService notificationService;

    @PostMapping("/save")
    public ResponseEntity<Object> writeReviewComment(@RequestBody ProjectReviewCommentRequestDTO commentDTO) {
        Member member = memberService.findByEmail(commentDTO.getMemberEmail());
        ProjectReview projectReview = projectReviewService.findById(commentDTO.getReviewId());
        ProjectReviewComment comment = projectReviewCommentService.saveComment(commentDTO, member, projectReview);
        notificationService.save(comment);
        return ResponseEntity.ok().body(comment.toDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReviewComment(@PathVariable UUID id, @RequestParam String memberEmail) {
        projectReviewCommentService.deleteById(id, memberEmail);
        return ResponseEntity.ok().body(new MessageResponseDTO(id + SuccessMessage.DELETE.getMessage()));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateReviewComment(@RequestBody CommentModifyRequestDTO commentDTO) {
        projectReviewCommentService.update(commentDTO);
        return ResponseEntity.ok().body(new MessageResponseDTO(commentDTO.getId() + SuccessMessage.UPDATE.getMessage()));
    }
}
