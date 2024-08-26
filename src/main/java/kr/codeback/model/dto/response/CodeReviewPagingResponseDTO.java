package kr.codeback.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
public class CodeReviewPagingResponseDTO {
    private int totalPage;
    private List<CodeReviewResponseDTO> reviews;
    @Builder
    private CodeReviewPagingResponseDTO(int totalPage, List<CodeReviewResponseDTO> reviews) {
        this.reviews = reviews;
        this.totalPage = totalPage;
    }
}
