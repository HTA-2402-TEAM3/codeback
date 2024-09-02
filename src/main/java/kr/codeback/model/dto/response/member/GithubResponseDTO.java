package kr.codeback.model.dto.response.member;

import java.util.Map;

public class GithubResponseDTO implements OAuth2ResponseDTO {

	private final Map<String, Object> attribute;

	public GithubResponseDTO(Map<String, Object> attribute) {

		this.attribute = (Map<String, Object>) attribute.get("response");
	}

	@Override
	public String getProvider() {

		return "github";
	}

	@Override
	public String getProviderId() {

		return attribute.get("id").toString();
	}

	@Override
	public String getEmail() {

		return attribute.get("email").toString();
	}

	@Override
	public String getName() {

		return attribute.get("name").toString();
	}
}