package kr.codeback.model.dto.response.review;

import kr.codeback.model.entity.CodeReviewComment;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
public class CodeReviewCommentResponseDTO {
    private final UUID id;
    private final UUID codeReviewId;
    private final String memberNickname;
    private final String commentContent;
    private final Timestamp createDate;

    @Builder
    public CodeReviewCommentResponseDTO(UUID id,UUID codeReviewId, String memberNickname, String commentContent, Timestamp createDate){
        this.id = id;
        this.codeReviewId = codeReviewId;
        this.memberNickname = memberNickname;
        this.commentContent = commentContent;
        this.createDate = createDate;
    }
}
