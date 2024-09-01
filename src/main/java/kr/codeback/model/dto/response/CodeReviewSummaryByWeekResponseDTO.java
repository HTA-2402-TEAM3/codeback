package kr.codeback.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CodeReviewSummaryByWeekResponseDTO {
	private int weekDiff; // 주차 차이
	private long count;   // 게시글 수
}
