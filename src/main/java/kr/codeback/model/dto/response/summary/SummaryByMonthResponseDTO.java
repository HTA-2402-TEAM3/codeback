package kr.codeback.model.dto.response.summary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SummaryByMonthResponseDTO {

	private int month;
	private long count;

}
