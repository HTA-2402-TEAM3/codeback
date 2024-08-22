package kr.codeback.model.dto.response;

import java.sql.Timestamp;
import java.util.UUID;

import kr.codeback.model.entity.CodeLanguageCategory;
import kr.codeback.model.entity.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CodeReviewResponseDTO {

	private UUID id;
	private Member member;
	private String title;
	private String content;
	private Timestamp createDate;
	private CodeLanguageCategory codeLanguageCategory;

}
