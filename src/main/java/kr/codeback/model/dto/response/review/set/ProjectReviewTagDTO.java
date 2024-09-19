package kr.codeback.model.dto.response.review.set;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ProjectReviewTagDTO {
    private final UUID id;
    private final String tag;

    @Builder
    public ProjectReviewTagDTO(UUID id, String tag) {
        this.id = id;
        this.tag = tag;
    }
}
