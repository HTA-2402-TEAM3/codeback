package kr.codeback.model.dto.response.review;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
public class ProjectReviewCommentResponseDTO {
    private final UUID id;
    private final UUID projectReviewId;
    private final String memberNickname;
    private final String commentContent;
    private final Timestamp createDate;

    @Builder
    public ProjectReviewCommentResponseDTO(UUID id,UUID projectReviewId, String memberNickname, String commentContent, Timestamp createDate){
        this.id = id;
        this.projectReviewId = projectReviewId;
        this.memberNickname = memberNickname;
        this.commentContent = commentContent;
        this.createDate = createDate;
    }
}
