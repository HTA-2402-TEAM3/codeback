package kr.codeback.model.dto.response.review;

import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.ProjectReviewComment;
import kr.codeback.model.entity.ProjectReviewImage;
import kr.codeback.model.entity.ProjectReviewTag;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
public class ProjectReviewResponseDTO {
    private final UUID id;
    private final Member member;
    private final String title;
    private final String content;
    private final Timestamp createDate;
    private final Set<ProjectReviewTag> projectReviewTags;
    private final List<ProjectReviewComment> projectReviewComments;
    private final Set<ProjectReviewImage> projectReviewImages;
    private final Integer preferenceCnt;
    private final String githubURL;

    @Builder
    public ProjectReviewResponseDTO(UUID id, Member member, String title, String content, Timestamp createDate,
                                    Set<ProjectReviewTag> projectReviewTags, List<ProjectReviewComment> projectReviewComments,
                                    Set<ProjectReviewImage> projectReviewImages, Integer preferenceCnt, String githubURL) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.projectReviewTags = projectReviewTags;
        this.projectReviewComments = projectReviewComments;
        this.projectReviewImages = projectReviewImages;
        this.preferenceCnt = preferenceCnt;
        this.githubURL = githubURL;
    }
}
