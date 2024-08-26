package kr.codeback.service.interfaces;

import java.util.List;
import java.util.Optional;

import kr.codeback.model.dto.response.MembersWithPageResponseDTO;
import kr.codeback.model.entity.Member;

public interface MemberService {

	// 멤버 생성 메서드
	Boolean saveMember(Member member);

	//멤버를 이메일로 찾는 메서드
	Member findByEmail(String email);

	//멤버를 닉네임으로 찾는 메서드
	Optional<Member> findMemberByNickname(String nickname);

	//모든 멤버 찾기 메서드
	List<Member> findAll();

	//멤버 수정 메서드, 수정 시 true 반환
	Boolean updateMember(Member member);

	//멤버 삭제 메서드, 삭제 시 true 반환
	Boolean deleteMemberByEmail(String email);

	//관리자 계정으로 멤버 찾는 메서드
	MembersWithPageResponseDTO findAllUnderAdmin(Member adminMember, int pageNum, int pageSize);

	// 이메일로 회원 삭제
	void deleteByEmail(String deleteEmail);

	// 관리자 계정 찾기
	Member findAdminMemberByEmail(String email);

}
