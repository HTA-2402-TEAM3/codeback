package kr.codeback.controller;


import kr.codeback.model.dto.request.CodeReviewRequestDTO;
import kr.codeback.model.dto.response.review.CodeReviewPagingResponseDTO;
import kr.codeback.service.interfaces.CodeReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
@Slf4j
public class CodeReviewRestController {
    private final CodeReviewService codeReviewService;

    @GetMapping("/{language}")
    public ResponseEntity<Object> languageSort(@PathVariable UUID language, @RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
                                               @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
                                               @RequestParam(required = false, defaultValue = "createDate", value = "sort") String sort) {

        CodeReviewPagingResponseDTO reviews = CodeReviewPagingResponseDTO.builder()
                .reviews(codeReviewService
                        .findCodeReviewByLanguage(language, pageNum, pageSize, sort).getContent())
                .totalPage(codeReviewService.findCodeReviewByLanguage(language, pageNum, pageSize, sort).getTotalPages())
                .currentPage(pageNum)
                .build();

        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/")
    public ResponseEntity<Object> allPages(@RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
                                           @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
                                           @RequestParam(required = false, defaultValue = "createDate", value = "sort") String sort) {

        CodeReviewPagingResponseDTO reviews = CodeReviewPagingResponseDTO.builder()
                .reviews(codeReviewService.findAllWithPage(pageNum, pageSize, sort).getContent())
                .totalPage(codeReviewService.findAllWithPage(pageNum, pageSize, sort).getTotalPages())
                .currentPage(pageNum)
                .build();

        return ResponseEntity.ok().body(reviews);
    }
    @PostMapping("/save")
    public ResponseEntity<Object> writeReview(@RequestBody CodeReviewRequestDTO codeReview) {
        codeReviewService.saveCodeReview(codeReview);
        return ResponseEntity.ok().body("{\"status\":\"success\"}");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable UUID id) {
        codeReviewService.deleteCodeReviewById(id);
        return ResponseEntity.ok().body("{\"status\":\"success\"}");
    }
}
