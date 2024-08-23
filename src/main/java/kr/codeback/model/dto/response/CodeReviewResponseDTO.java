package kr.codeback.model.dto.response;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;
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
	private final List<CodeReviewComment> codeReviewComments;

	@Builder
	private CodeReviewResponseDTO(UUID id, Member member, String title, String content, Timestamp createDate,
		String codeLanguageName, List<CodeReviewComment> codeReviewComments) {
		this.id = id;
		this.member = member;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.codeLanguageName = codeLanguageName;
		this.codeReviewComments = codeReviewComments;
	}
}
