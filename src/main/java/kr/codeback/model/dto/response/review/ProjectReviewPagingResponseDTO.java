package kr.codeback.model.dto.response.review;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProjectReviewPagingResponseDTO {
    private final int totalPage;
    private final List<ProjectReviewListResponseDTO> reviews;
    private final int currentPage;
    @Builder
    private ProjectReviewPagingResponseDTO(int totalPage, List<ProjectReviewListResponseDTO> reviews, int currentPage) {
        this.reviews = reviews;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }
}
