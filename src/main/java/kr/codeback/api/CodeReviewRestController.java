package kr.codeback.api;


import kr.codeback.common.MessageResponseDTO;
import kr.codeback.model.constant.SuccessMessage;
import kr.codeback.model.dto.request.review.CodeReviewRequestDTO;
import kr.codeback.model.dto.response.review.CodeReviewListResponseDTO;
import kr.codeback.model.dto.response.review.CodeReviewPagingResponseDTO;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.service.interfaces.CodeReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class CodeReviewRestController {
    private final CodeReviewService codeReviewService;

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam(required = false) String search, @RequestParam(required = false) UUID language,
                                         @RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
                                         @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
                                         @RequestParam(required = false, defaultValue = "createDate", value = "sort") String sort) {
        
        Page<CodeReviewListResponseDTO> page = codeReviewService.findWithFilters(search,language, pageNum, pageSize, sort);

        CodeReviewPagingResponseDTO reviews = CodeReviewPagingResponseDTO.builder()
                .reviews(page.getContent())
                .totalPage(page.getTotalPages())
                .currentPage(pageNum)
                .build();

        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/")
    public ResponseEntity<Object> allPages(@RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
                                           @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
                                           @RequestParam(required = false, defaultValue = "createDate", value = "sort") String sort) {
        Page<CodeReviewListResponseDTO> page = codeReviewService.findAllWithPage(pageNum, pageSize, sort);

        CodeReviewPagingResponseDTO reviews = CodeReviewPagingResponseDTO.builder()
                .reviews(page.getContent())
                .totalPage(page.getTotalPages())
                .currentPage(pageNum)
                .build();

        return ResponseEntity.ok().body(reviews);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getReview(@PathVariable UUID id){
        CodeReview codeReview = codeReviewService.findById(id);
        return ResponseEntity.ok().body(codeReview);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> writeReview(@RequestBody CodeReviewRequestDTO codeReview) {
        codeReviewService.saveCodeReview(codeReview);
        return ResponseEntity.ok().body(new MessageResponseDTO(codeReview+SuccessMessage.CREATE.getMessage()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable UUID id) {
        codeReviewService.deleteCodeReviewById(id);
        return ResponseEntity.ok().body(new MessageResponseDTO(id+SuccessMessage.DELETE.getMessage()));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateReview(@RequestBody CodeReviewRequestDTO reviewDTO) {
        codeReviewService.updateCodeReview(reviewDTO);
        return ResponseEntity.ok().body(new MessageResponseDTO(SuccessMessage.UPDATE.getMessage()));
    }
}
