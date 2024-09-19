package kr.codeback.model.dto.response.review;

import kr.codeback.model.dto.response.review.set.ProjectReviewImageDTO;
import kr.codeback.model.dto.response.review.set.ProjectReviewTagDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
public class ProjectReviewModifyResponseDTO {
    private final String title;
    private final String content;
    private final Set<ProjectReviewImageDTO> images;
    private final Set<ProjectReviewTagDTO> tags;
    private final String githubUrl;

    @Builder
    public ProjectReviewModifyResponseDTO(String title, String content, Set<ProjectReviewImageDTO> images, Set<ProjectReviewTagDTO> tags, String githubUrl) {
        this.title = title;
        this.content = content;
        this.images = images;
        this.tags = tags;
        this.githubUrl = githubUrl;
    }
}
