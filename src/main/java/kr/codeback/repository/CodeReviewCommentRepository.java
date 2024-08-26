package kr.codeback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.CodeReviewComment;
import lombok.NonNull;

@Repository
public interface CodeReviewCommentRepository extends JpaRepository<CodeReviewComment, UUID> {

	@Modifying
	@Query("""
		delete from CodeReviewComment c
		where c.member.email = :email
		""")
	void deleteAllByEmail(@NonNull String email);

	@Query("""
		select c from CodeReviewComment c
		join fetch c.member
		join fetch c.codeReview
		join fetch c.baseComment
		where c.member.email = :email
		""")
	void findByEmail(String email);
}
