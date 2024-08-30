package kr.codeback.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MemberSummaryResponseDTO {

	private long totalCount;
	private long activeCount;
	private long inactiveCount;

}
