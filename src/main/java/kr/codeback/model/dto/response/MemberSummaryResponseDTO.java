package kr.codeback.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSummaryResponseDTO {

	private long totalCount;
	private long activeCount;
	private long inactiveCount;

}
