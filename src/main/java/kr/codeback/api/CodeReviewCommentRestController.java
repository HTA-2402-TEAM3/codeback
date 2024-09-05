package kr.codeback.api;

import kr.codeback.common.MessageResponseDTO;
import kr.codeback.model.constant.SuccessMessage;
import kr.codeback.model.dto.request.review.CodeReviewCommentRequestDTO;
import kr.codeback.model.dto.request.review.CommentModifyRequestDTO;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.service.impl.CodeReviewCommentServiceImpl;
import kr.codeback.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/review/comment")
@RequiredArgsConstructor
public class CodeReviewCommentRestController {
    private final CodeReviewCommentServiceImpl codeReviewCommentService;
    private final NotificationService notificationService;
    @PostMapping("/save")
    public ResponseEntity<Object> writeReviewComment (@RequestBody CodeReviewCommentRequestDTO commentDTO) {
        CodeReviewComment comment = codeReviewCommentService.saveComment(commentDTO);

        //notification 생성
        notificationService.save(comment);

        return ResponseEntity.ok().body(comment.toDTO());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable UUID id) {
        codeReviewCommentService.deleteById(id);
        return ResponseEntity.ok().body(new MessageResponseDTO(SuccessMessage.DELETE.getMessage()));
    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateComment(@RequestBody CommentModifyRequestDTO commentDTO) {
        codeReviewCommentService.update(commentDTO);
        return ResponseEntity.ok().body(new MessageResponseDTO(SuccessMessage.UPDATE.getMessage()));
    }

}
