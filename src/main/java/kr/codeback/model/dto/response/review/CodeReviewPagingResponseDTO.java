package kr.codeback.model.dto.response.review;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CodeReviewPagingResponseDTO {
    private int totalPage;
    private List<CodeReviewListResponseDTO> reviews;
    private int currentPage;
    @Builder
    private CodeReviewPagingResponseDTO(int totalPage, List<CodeReviewListResponseDTO> reviews, int currentPage) {
        this.reviews = reviews;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }
}
