package kr.codeback.model.dto.response.summary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodeReviewPreferenceSummaryResponseDTO {

	private int month;
	private long count;

}
