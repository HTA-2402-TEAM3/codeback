package kr.codeback.model.dto.response.review;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeReviewCommentResponseDTO {
	private UUID id;
	private String nickname;
	private String commentContent;
	private Timestamp createDate;
	private long preferenceCnt;

	@Builder
	public CodeReviewCommentResponseDTO(UUID id, UUID codeReviewId, String nickname, String commentContent,
		Timestamp createDate, long preferenceCnt) {
		this.id = id;
		this.nickname = nickname;
		this.commentContent = commentContent;
		this.createDate = createDate;
		this.preferenceCnt = preferenceCnt;
	}
}
