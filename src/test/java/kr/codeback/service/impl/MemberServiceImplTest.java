package kr.codeback.service.impl;

import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.codeback.model.dto.response.AuthorityResponseDTO;
import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.MemberRepository;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.interfaces.NotificationService;
import kr.codeback.service.interfaces.PreferenceService;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

	private MemberService memberService;

	private MemberRepository memberRepository;
	private NotificationService notificationService;
	private PreferenceService PreferenceService;

	@BeforeEach
	void setUpMemberService() {
		memberRepository = mock(MemberRepository.class);
		notificationService = mock(NotificationService.class);
		PreferenceService = mock(PreferenceService.class);
		memberService = new MemberServiceImpl(memberRepository, notificationService, PreferenceService);
	}

	@Test
	void findByEmail() {
		Member member = new Member();
	}

	@Test
	@DisplayName("회원 권한 변경 테스트")
	public void testChangeAuthority() {

		Authority adminAuthority = Authority.builder()
			.id(UUID.fromString("aaaaaaaa-1111-1111-1111-111111111111"))
			.name("ROLE_ADMIN")
			.build();

		Authority userAuthority = Authority.builder()
			.id(UUID.fromString("bbbbbbbb-1111-1111-1111-111111111111"))
			.name("ROLE_USER")
			.build();

		Member member = Member.builder()
			.id(UUID.fromString("cccccccc-1111-1111-1111-111111111111"))
			.email("aa@test.com")
			.nickname("aaaaa")
			.authority(userAuthority)
			.build();

		when(memberRepository.findByEmail("aa@test.com")).thenReturn(Optional.of(member));

		AuthorityResponseDTO authorityResponseDTO = memberService.updateAuthority("aa@test.com", adminAuthority);

		Assertions.assertEquals(authorityResponseDTO.getAuthorityName(), "ROLE_ADMIN");

	}

}