package kr.codeback.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.exception.ErrorCode;
import kr.codeback.exception.member.MemberNotFoundException;
import kr.codeback.exception.member.WrongAuthorityException;
import kr.codeback.model.constant.CustomOAuth2User;
import kr.codeback.model.dto.response.AuthorityResponseDTO;
import kr.codeback.model.dto.response.MemberSummaryResponseDTO;
import kr.codeback.model.dto.response.member.MemberResponseDTO;
import kr.codeback.model.dto.response.member.MembersWithPageResponseDTO;
import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.MemberRepository;
import kr.codeback.service.interfaces.AuthorityService;
import kr.codeback.service.interfaces.PreferenceService;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final NotificationService notificationService;
	private final PreferenceService preferenceService;
	private final AuthorityService authorityService;
	private final CodeReviewPreferenceService codeReviewPreferenceService;

	@Override
	public String extractEmail() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof OAuth2User) {
			CustomOAuth2User oauth2User = (CustomOAuth2User) principal;
			return oauth2User.getName(); // OAuth2 서비스에서 제공하는 속성명.
		} else {
			UserDetails userDetails = (UserDetails) principal;
			return userDetails.getUsername();
		}

	}

	@Override
	public Member extractMember() {
		return memberRepository.findByEmail(extractEmail()).get();
	}


	@Override
	public Boolean save(Member member) {
		if (memberRepository.findByEmail(member.getEmail()).isEmpty()) {
			memberRepository.save(member);
			return true;
		}
		return false;
	}

	@Override
	public Member findByEmail(String email) {

		Optional<Member> optionalMember = memberRepository.findByEmail(email);

		return optionalMember.orElseThrow(
			() -> new MemberNotFoundException(
				ErrorCode.NOT_EXIST_USER.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()
			)
		);
	}

	@Override
	public MemberSummaryResponseDTO getMemberSummary() {
		return memberRepository.calculateSummary().orElseThrow(
			() -> new IllegalStateException("잘못된 접근")
		);
	}

	@Override
	public Optional<Member> findMemberByNickname(String nickname) {
		return Optional.empty();
	}

	@Override
	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	@Override
	public Boolean update(Member member) {
		memberRepository.save(member);
		return true;
	}

	@Override
	public Boolean deleteMemberByEmail(String email) {
		return null;
	}

	@Override
	public MembersWithPageResponseDTO findAllUnderAdmin(int pageNum, int pageSize) {

		Pageable pageable = PageRequest.of(pageNum, pageSize);

		Page<MemberResponseDTO> pageMemberResponseDTO = memberRepository.findByDeleteSignIsFalse(pageable)
			.map(member -> MemberResponseDTO.builder()
				.email(member.getEmail())
				.nickname(member.getNickname())
				.authorityName(member.getAuthority().getName())
				.build()
			);

		return MembersWithPageResponseDTO.builder()
			.memberResponseDTOs(pageMemberResponseDTO.getContent())
			.totalElements(pageMemberResponseDTO.getTotalElements())
			.totalPages(pageMemberResponseDTO.getTotalPages())
			.build();
	}

	@Override
	@Transactional
	public void softDeleteByEmail(String email) {

		Member deleteMember = findByEmail(email);

		notificationService.deleteByMember(deleteMember);
		preferenceService.deleteByMember(deleteMember);

		deleteMember.deleteMember();
		memberRepository.save(deleteMember);
	}

	@Override
	public void validateAdminMemberByEmail(String email) {

		Member member = findByEmail(email);

		if (!member.isAdmin()) {
			throw new WrongAuthorityException(
				ErrorCode.WRONG_AUTHORITY.getStatus(),
				ErrorCode.WRONG_AUTHORITY.getMessage()
			);
		}
	}

	@Override
	public Member findById(UUID uuid) {
		return memberRepository.findById(uuid).get();
	}

	@Override
	public AuthorityResponseDTO updateAuthority(String email, Authority authority) {

		Member member = findByEmail(email);

		member.changeAuthority(authority);
		update(member);

		return new AuthorityResponseDTO(member.getEmail(), authority.getName());
	}

}
