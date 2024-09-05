package kr.codeback.model.dto.response.summary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CodeReviewCommentSummaryResponseDTO {

	private int month;
	private long count;

}
