package kr.codeback.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

	Optional<Member> findByEmail(String email);

	Page<Member> findByDeleteSignIsFalse(Pageable pageable);
}
