package kr.codeback.model.dto.response.review;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CodeReviewResponseDTO {

	private final UUID id;
	private final Member member;
	private final String title;
	private final String content;
	private final Timestamp createDate;
	private final String codeLanguageName;
	private final List<CodeReviewCommentResponseDTO> codeReviewComments;
	private final int preferenceCnt;

	@Builder
	private CodeReviewResponseDTO(UUID id, String member, String title, String content, Timestamp createDate,
		String codeLanguageName, List<CodeReviewCommentResponseDTO> codeReviewComments,
		Integer preferenceCnt) {
		this.id = id;
		this.member = member;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.codeLanguageName = codeLanguageName;
		this.codeReviewComments = codeReviewComments;
		this.preferenceCnt = preferenceCnt;
	}
}
