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
    private final String email;
    private final String nickname;
    private final String title;
    private final String content;
    private final Timestamp createDate;
    private final Set<ProjectReviewTag> projectReviewTags;
    private final List<ProjectReviewCommentResponseDTO> projectReviewComments;
    private final Set<ProjectReviewImage> projectReviewImages;
    private final long preferenceCnt;
    private final String githubURL;

    @Builder
    public ProjectReviewResponseDTO(UUID id, String email, String nickname, String title, String content, Timestamp createDate,
                                    Set<ProjectReviewTag> projectReviewTags, List<ProjectReviewCommentResponseDTO> projectReviewComments,
                                    Set<ProjectReviewImage> projectReviewImages, Integer preferenceCnt, String githubURL) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
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
