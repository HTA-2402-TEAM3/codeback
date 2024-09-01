package kr.codeback.model.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CodeReviewSummaryResponseDTO {

	private List<CodeReviewSummaryByLanguageResponseDTO> codeReviewSummaryByLanguageResponseDTOs;
	private long totalCount;

}
