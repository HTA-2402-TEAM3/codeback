package kr.codeback.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.exception.ErrorCode;
import kr.codeback.exception.member.MemberNotFoundException;
import kr.codeback.exception.member.WrongAuthorityException;
import kr.codeback.model.dto.response.member.MemberResponseDTO;
import kr.codeback.model.dto.response.member.MembersWithPageResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.MemberRepository;
import kr.codeback.service.interfaces.CodeReviewCommentService;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import kr.codeback.service.interfaces.CodeReviewService;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	private final CodeReviewCommentService codeReviewCommentService;
	private final CodeReviewService codeReviewService;
	private final NotificationService notificationService;
	private final CodeReviewPreferenceService codeReviewPreferenceService;

	@Override
	public Boolean save(Member member) {
		if(memberRepository.findByEmail(member.getEmail()).isEmpty()){
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
		codeReviewPreferenceService.deleteByMember(deleteMember);

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
	public Member findById(UUID uuid){
		return memberRepository.findById(uuid).get();
	}

}
