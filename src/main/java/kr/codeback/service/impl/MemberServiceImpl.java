package kr.codeback.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.codeback.exception.ErrorCode;
import kr.codeback.exception.member.MemberNotFoundException;
import kr.codeback.exception.member.WrongAuthorityException;
import kr.codeback.model.dto.response.MemberResponseDTO;
import kr.codeback.model.dto.response.MembersWithPageResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.MemberRepository;
import kr.codeback.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public Boolean saveMember(Member member) {
		memberRepository.save(member);
		return null;
	}

	@Override
	public Member findByEmail(String email) {

		Optional<Member> optionalMember = memberRepository.findById(email);

		return optionalMember.orElseThrow(
			() -> new MemberNotFoundException(
				ErrorCode.NOT_EXIST_USER.getStatus(),
				ErrorCode.NOT_EXIST_USER.getMessage()
			)
		);

	}

	@Override
	public Optional<Member> findMemberByNickname(String nickname) {
		return Optional.empty();
	}

	@Override
	public List<Member> findAll() {
		return List.of();
	}

	@Override
	public Boolean updateMember(Member member) {
		return null;
	}

	@Override
	public Boolean deleteMemberByEmail(String email) {
		return null;
	}

	@Override
	public MembersWithPageResponseDTO findAllUnderAdmin(Member adminMember, int pageNum, int pageSize) {

		if (!adminMember.isAdmin()) {
			throw new WrongAuthorityException(
				ErrorCode.WRONG_AUTHORITY.getStatus(),
				ErrorCode.WRONG_AUTHORITY.getMessage()
			);
		}

		Pageable pageable = PageRequest.of(pageNum, pageSize);

		Page<MemberResponseDTO> pageMemberResponseDTO = memberRepository.findAll(pageable)
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

}
