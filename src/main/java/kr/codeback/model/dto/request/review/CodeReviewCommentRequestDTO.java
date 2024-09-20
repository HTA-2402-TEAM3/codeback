package kr.codeback.model.dto.request.review;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CodeReviewCommentRequestDTO {
    private final UUID reviewId;
    private final String memberEmail;
    private final String content;

    @Builder
    public CodeReviewCommentRequestDTO(UUID reviewId, String memberEmail, String content) {
        this.reviewId = reviewId;
        this.memberEmail = memberEmail;
        this.content = content;
    }
}
