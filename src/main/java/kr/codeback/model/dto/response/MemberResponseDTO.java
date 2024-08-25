package kr.codeback.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDTO {

	private final String email;
	private final String nickname;
	private final String authorityName;

	@Builder
	private MemberResponseDTO(String email, String nickname, String authorityName) {
		this.email = email;
		this.nickname = nickname;
		this.authorityName = authorityName;
	}
}
