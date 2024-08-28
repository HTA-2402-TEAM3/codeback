package kr.codeback.service.impl;

import org.springframework.stereotype.Service;

import kr.codeback.model.entity.Member;
import kr.codeback.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

	private final MemberRepository memberRepository;

	public void saveMember(Member member){
		memberRepository.save(member);
	}
}
