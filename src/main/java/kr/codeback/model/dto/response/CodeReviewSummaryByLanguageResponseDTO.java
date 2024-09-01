package kr.codeback.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CodeReviewSummaryByLanguageResponseDTO {

	private String languageName;
	private long totalCount;

}
