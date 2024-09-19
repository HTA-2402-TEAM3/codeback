package kr.codeback.model.dto.response.review;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectReviewListResponseDTO {
	private UUID id;
	private String member;
	private String title;
	private Timestamp createDate;
	private List<String> projectReviewTags;
	private String projectReviewThumbnails;
	private Integer projectReviewComments;
	private long preferenceCnt;

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
