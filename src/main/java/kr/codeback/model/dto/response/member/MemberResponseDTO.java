package kr.codeback.model.dto.response.member;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDTO {

	private final UUID uuid;
	private final String email;
	private final String nickname;
	private final String authorityName;

	@Builder
	private MemberResponseDTO(UUID uuid, String email, String nickname, String authorityName) {
		this.uuid = uuid;
		this.email = email;
		this.nickname = nickname;
		this.authorityName = authorityName;
	}

}
