package kr.codeback.model.dto.response.review;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//	Json 시리얼화 추후에 DTO 변경
	private final List<CodeReviewComment> codeReviewComments;
	private final Integer preferenceCnt;

	@Builder
	private CodeReviewResponseDTO(UUID id, Member member, String title, String content, Timestamp createDate,
                                  String codeLanguageName, List<CodeReviewComment> codeReviewComments, Integer preferenceCnt) {
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
