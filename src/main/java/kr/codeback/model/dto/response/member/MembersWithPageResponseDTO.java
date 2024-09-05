package kr.codeback.model.dto.response.member;

import java.util.List;

import kr.codeback.model.dto.response.member.MemberResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MembersWithPageResponseDTO {

	private final List<MemberResponseDTO> memberResponseDTOs;
	private final long totalElements;
	private final int totalPages;

	@Builder
	private MembersWithPageResponseDTO(List<MemberResponseDTO> memberResponseDTOs, long totalElements, int totalPages) {
		this.memberResponseDTOs = memberResponseDTOs;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}
}
