package kr.codeback.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.dto.response.MemberSummaryResponseDTO;
import kr.codeback.model.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

	Optional<Member> findByEmail(String memberEmail);
	//    use write CodeReview... => request member email 로 Member 객체 찾음

	Page<Member> findByDeleteSignIsFalse(Pageable pageable);

	@Query("""
		select
		new kr.codeback.model.dto.response.MemberSummaryResponseDTO(
		count(m),
		count(case when m.deleteSign = false then 1 else null end),
		count(case when m.deleteSign = true then 1 else null end)
		)
		from Member m
		""")
	Optional<MemberSummaryResponseDTO> calculateSummary();
}
