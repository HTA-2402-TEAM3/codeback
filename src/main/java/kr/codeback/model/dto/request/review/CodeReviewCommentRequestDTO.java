package kr.codeback.model.dto.request.review;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CodeReviewCommentRequestDTO {
    private final UUID codeReviewId;
    private final String memberEmail;
    private final String content;

    @Builder
    public CodeReviewCommentRequestDTO(UUID codeReviewId, String memberEmail, String content) {
        this.codeReviewId = codeReviewId;
        this.memberEmail = memberEmail;
        this.content = content;
    }
}
