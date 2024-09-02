package kr.codeback.model.dto.request.review;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CommentModifyRequestDTO {
    private final String content;
    private final UUID id;
    @Builder
    public CommentModifyRequestDTO(String content, UUID id) {
        this.id = id;
        this.content = content;
    }
}