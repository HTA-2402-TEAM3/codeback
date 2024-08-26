package kr.codeback.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public class UserResponseDTO {
	String email;
	String nickname;
}
