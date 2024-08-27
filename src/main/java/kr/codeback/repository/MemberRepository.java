package kr.codeback.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID>{
    Optional<Member> findByEmail(String memberEmail);
//    use write CodeReview... => request member email 로 Member 객체 찾음
}
