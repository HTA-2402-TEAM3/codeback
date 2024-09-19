package kr.codeback.model.dto.request.review;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ProjectReviewCommentRequestDTO {
    private final UUID reviewId;
    private final String memberEmail;
    private final String content;

    @Builder
    public ProjectReviewCommentRequestDTO(UUID reviewId, String memberEmail, String content) {
        this.reviewId = reviewId;
        this.memberEmail = memberEmail;
        this.content = content;
    }
}
