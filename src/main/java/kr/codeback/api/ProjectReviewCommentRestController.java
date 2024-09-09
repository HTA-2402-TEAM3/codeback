package kr.codeback.api;

import kr.codeback.common.MessageResponseDTO;
import kr.codeback.model.constant.SuccessMessage;
import kr.codeback.model.dto.request.review.CodeReviewCommentRequestDTO;
import kr.codeback.model.dto.request.review.CommentModifyRequestDTO;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.service.impl.CodeReviewCommentServiceImpl;
import kr.codeback.service.interfaces.ProjectReviewCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/review/comment")
@RequiredArgsConstructor
public class ProjectReviewCommentRestController {
//        private final ProjectReviewCommentService projectReviewCommentService;
//        @PostMapping("/save")
//        public ResponseEntity<Object> writeReviewComment (@RequestBody CodeReviewCommentRequestDTO commentDTO) {
//            CodeReviewComment comment = projectReviewCommentService.saveComment(commentDTO);
//            return ResponseEntity.ok().body(comment.toDTO());
//        }
//        @DeleteMapping("/{id}")
//        public ResponseEntity<Object> deleteComment(@PathVariable UUID id, @RequestParam String memberEmail) {
//            codeReviewCommentService.deleteById(id, memberEmail);
//            return ResponseEntity.ok().body(new MessageResponseDTO(SuccessMessage.DELETE.getMessage()));
//        }
//        @PutMapping("/update")
//        public ResponseEntity<Object> updateComment(@RequestBody CommentModifyRequestDTO commentDTO) {
//            codeReviewCommentService.update(commentDTO);
//            return ResponseEntity.ok().body(new MessageResponseDTO(SuccessMessage.UPDATE.getMessage()));
//        }






}
