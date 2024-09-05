package kr.codeback.model.constant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

	private final OAuthProfile oauthProfile;


	@Override
	public Map<String, Object> getAttributes() {

		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collection = new ArrayList<>();

		collection.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {

				return oauthProfile.getRole();
			}
		});

		return collection;
	}

	//name이 이메일
	@Override
	public String getName() {

		return oauthProfile.getName();
	}

	//username이 닉네임
	public String getUsername() {

		return oauthProfile.getUsername();
	}
}