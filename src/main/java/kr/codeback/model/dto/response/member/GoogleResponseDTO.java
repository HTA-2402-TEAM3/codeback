package kr.codeback.model.dto.response.member;

import java.util.Map;

public class GoogleResponseDTO implements OAuth2ResponseDTO {

	private final Map<String, Object> attribute;

	public GoogleResponseDTO(Map<String, Object> attribute) {

		this.attribute = attribute;
	}

	@Override
	public String getProvider() {

		return "google";
	}

	@Override
	public String getProviderId() {

		return attribute.get("sub").toString();
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