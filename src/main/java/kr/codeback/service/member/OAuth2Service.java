package kr.codeback.service.member;



import java.util.Optional;
import java.util.UUID;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.codeback.model.constant.CustomOAuth2User;
import kr.codeback.model.constant.OAuthProfile;
import kr.codeback.model.dto.response.member.GithubResponseDTO;
import kr.codeback.model.dto.response.member.GoogleResponseDTO;
import kr.codeback.model.dto.response.member.OAuth2ResponseDTO;
import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.MemberRepository;
import kr.codeback.service.interfaces.AuthorityService;
import kr.codeback.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2Service extends DefaultOAuth2UserService {

	private final MemberService memberService;
	private final MemberRepository memberRepository;
	private final AuthorityService authorityService;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2User oAuth2User = super.loadUser(userRequest);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		OAuth2ResponseDTO oAuth2ResponseDTO = null;

		if (registrationId.equals("github")) {

			oAuth2ResponseDTO = new GithubResponseDTO(oAuth2User.getAttributes());
		}
		else if (registrationId.equals("google")) {

			oAuth2ResponseDTO = new GoogleResponseDTO(oAuth2User.getAttributes());
		}
		else {

			return null;
		}

		String username = oAuth2ResponseDTO.getName()+"#"+oAuth2ResponseDTO.getProvider();
		Optional<Member> existData = memberRepository.findByEmail(oAuth2ResponseDTO.getEmail());

		Authority authority = authorityService.findByName("ROLE_USER");

		if (existData.isEmpty()) {

			Member memberEntity = Member.builder()
				.id(UUID.randomUUID())
				.authority(authority)
				.email(oAuth2ResponseDTO.getEmail())
				.nickname(username)
				.build();

			memberService.save(memberEntity);

			OAuthProfile oAuthProfile = OAuthProfile.builder()
				.role("ROLE_USER")
				.name(oAuth2ResponseDTO.getEmail())
				.username(username)
				.build();

			return new CustomOAuth2User(oAuthProfile);
		}
		else {

			OAuthProfile oAuthProfile = OAuthProfile.builder()
			.role("ROLE_USER")
			.name(oAuth2ResponseDTO.getEmail())
			.username(username)
			.build();

			return new CustomOAuth2User(oAuthProfile);
		}
	}
}
