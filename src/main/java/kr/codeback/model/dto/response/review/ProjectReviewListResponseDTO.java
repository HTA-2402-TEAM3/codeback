package kr.codeback.model.dto.response.review;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
public class ProjectReviewListResponseDTO {
    private final UUID id;
    private final String member;
    private final String title;
    private final Timestamp createDate;
    private final List<String> projectReviewTags;
    private final String projectReviewThumbnails;
    private final Integer projectReviewComments;
    private final Integer preferenceCnt;
    @Builder
    public ProjectReviewListResponseDTO(UUID id, String member, String title, Timestamp createDate,
                                        List<String> projectReviewTags, String projectReviewThumbnails,
                                        Integer projectReviewComments, Integer preferenceCnt) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.createDate = createDate;
        this.projectReviewTags = projectReviewTags;
        this.projectReviewThumbnails = projectReviewThumbnails;
        this.projectReviewComments = projectReviewComments;
        this.preferenceCnt = preferenceCnt;
    }
}
