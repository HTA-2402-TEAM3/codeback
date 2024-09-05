package kr.codeback.model.constant;

import kr.codeback.model.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthProfile {
	private final String role;
	private final String name;
	private final String username;

	//name이 이메일 username이 닉네임
	@Builder
	public OAuthProfile(String role, String name, String username) {
		this.role = role;
		this.name = name;
		this.username = username;
	}
}