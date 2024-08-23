package kr.codeback.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.codeback.model.entity.Member;
import kr.codeback.repository.MemberRepository;
import kr.codeback.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	public Boolean save(Member member){
		memberRepository.save(member);
		return true;
	}

	@Override
	public Optional<Member> findByEmail(String email) {
		return memberRepository.findById(email);
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
	public Boolean updateMember(Member member) {
		return null;
	}

	@Override
	public Boolean deleteMemberByEmail(String email) {
		return null;
	}

}
