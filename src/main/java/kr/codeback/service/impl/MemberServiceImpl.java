package kr.codeback.service.impl;

import static org.hibernate.Hibernate.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
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
			() -> new EntityNotFoundException("존재하지 않는 회원입니다.")
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
			throw new IllegalStateException("권한이 없는 유저 입니다.");
		}

		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Member> pageMember = memberRepository.findAll(pageable);

		Page<MemberResponseDTO> pageMemberResponseDTO = pageMember.map(member -> MemberResponseDTO.builder()
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
