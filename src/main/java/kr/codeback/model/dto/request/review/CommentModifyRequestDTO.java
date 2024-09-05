package kr.codeback.model.dto.request.review;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CommentModifyRequestDTO {
    private final String content;
    private final UUID id;
    private final String memberEmail;
    @Builder
    public CommentModifyRequestDTO(String content, UUID id, String memberEmail) {
        this.id = id;
        this.content = content;
        this.memberEmail = memberEmail;
    }
}