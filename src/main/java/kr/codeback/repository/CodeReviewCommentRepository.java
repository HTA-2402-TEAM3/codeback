package kr.codeback.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;

@Repository
public interface CodeReviewCommentRepository extends JpaRepository<CodeReviewComment, UUID> {

	List<CodeReviewComment> findByMember(Member member);
}
