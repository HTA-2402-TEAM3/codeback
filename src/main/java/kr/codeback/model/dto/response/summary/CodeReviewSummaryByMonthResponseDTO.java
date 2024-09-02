package kr.codeback.model.dto.response.summary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CodeReviewSummaryByMonthResponseDTO {
	private int month; // 주차 차이
	private long count;   // 게시글 수
}
