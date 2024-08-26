package kr.codeback.controller;


import kr.codeback.model.dto.response.CodeReviewPagingResponseDTO;
import kr.codeback.model.dto.response.CodeReviewResponseDTO;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.service.interfaces.CodeReviewService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
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
                .build();

        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/")
    public ResponseEntity<Object> allPages(@RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
                                           @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
                                           @RequestParam(required = false, defaultValue = "createDate", value = "sort") String sort) {
        CodeReviewPagingResponseDTO reviews = CodeReviewPagingResponseDTO.builder()
                .reviews(codeReviewService.findCodeReviewAll(pageNum, pageSize, sort).getContent())
                .totalPage(codeReviewService.findCodeReviewAll(pageNum, pageSize, sort).getTotalPages())
                .build();

        return ResponseEntity.ok().body(reviews);
    }
}
