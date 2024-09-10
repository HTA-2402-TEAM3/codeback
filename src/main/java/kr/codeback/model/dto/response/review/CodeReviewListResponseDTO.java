package kr.codeback.model.dto.response.review;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CodeReviewListResponseDTO {
	private UUID id;
	private String member;
	private String title;
	private String content;
	private Timestamp createDate;
	private String codeLanguageName;
	private Integer codeReviewComments;

	@Setter
	private long preferenceCnt;

	@Builder
	private CodeReviewListResponseDTO(UUID id, String member, String title, String content, Timestamp createDate,
		String codeLanguageName, Integer codeReviewComments, long preferenceCnt) {
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
