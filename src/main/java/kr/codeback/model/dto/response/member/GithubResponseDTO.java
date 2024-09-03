package kr.codeback.model.dto.response.member;

import java.util.Map;

public class GithubResponseDTO implements OAuth2ResponseDTO {

	private final Map<String, Object> attribute;

	public GithubResponseDTO(Map<String, Object> attribute) {

		this.attribute = attribute;
	}

	@Override
	public String getProvider() {

		return "github";
	}

	@Override
	public String getProviderId() {

		Map<String, Object> id = (Map<String, Object>) attribute.get("id");
		return id.get("value").toString();
	}

	@Override
	public String getEmail() {

		return attribute.get("email").toString();
	}

	@Override
	public String getName() {

		return attribute.get("login").toString();
	}
}