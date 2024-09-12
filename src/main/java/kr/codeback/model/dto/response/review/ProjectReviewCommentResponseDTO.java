package kr.codeback.model.dto.response.review;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ProjectReviewCommentResponseDTO {
	private final UUID id;
	private final UUID projectReviewId;
	private final String email;
	private final String nickname;
	private final String commentContent;
	private final Timestamp createDate;
	private final long preferenceCnt;

	@Builder
	public ProjectReviewCommentResponseDTO(UUID id, UUID projectReviewId,String email, String nickname, String commentContent,
		Timestamp createDate,
		long preferenceCnt) {
		this.id = id;
		this.projectReviewId = projectReviewId;
		this.email = email;
		this.nickname = nickname;
		this.commentContent = commentContent;
		this.createDate = createDate;
		this.preferenceCnt = preferenceCnt;
	}
}
