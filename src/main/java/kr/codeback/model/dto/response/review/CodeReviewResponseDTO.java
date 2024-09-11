package kr.codeback.model.dto.response.review;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CodeReviewResponseDTO {

	private final UUID id;
// 	memberString
	private final String nickname;
	private final String email;
	private final String title;
	private final String content;
	private final Timestamp createDate;
	private final String codeLanguageName;
	private final List<CodeReviewCommentResponseDTO> codeReviewComments;
	private final int preferenceCnt;

	@Builder
	private CodeReviewResponseDTO(UUID id, String nickname, String email, String title, String content, Timestamp createDate,
                                  String codeLanguageName, List<CodeReviewCommentResponseDTO> codeReviewComments,
                                  Integer preferenceCnt) {
		this.id = id;
		this.nickname = nickname;
        this.email = email;
        this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.codeLanguageName = codeLanguageName;
		this.codeReviewComments = codeReviewComments;
		this.preferenceCnt = preferenceCnt;
	}
}
