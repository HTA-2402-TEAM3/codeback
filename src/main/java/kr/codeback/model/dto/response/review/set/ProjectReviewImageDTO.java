package kr.codeback.model.dto.response.review.set;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ProjectReviewImageDTO {
    private final UUID id;
    private final String fileName;
    private final String url;

    @Builder
    public ProjectReviewImageDTO(UUID id, String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
        this.id = id;
    }
}
