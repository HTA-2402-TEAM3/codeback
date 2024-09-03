package kr.codeback.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequestDTO {

	String email;
	String nickname;
	String role;

	public TokenRequestDTO(String email) {
		this.email = email;
	}
}
