package kr.codeback.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.CodeReview;
import lombok.NonNull;

@Repository
public interface CodeReviewRepository extends JpaRepository<CodeReview, UUID> {

	@Override
	@Query("""
		select cr from CodeReview cr
		join fetch cr.member
		join fetch cr.codeLanguageCategory
		left join fetch cr.comments
		where cr.id = :id
		""")
	Optional<CodeReview> findById(@NonNull UUID id);

	@Modifying
	@Query("""
		delete from CodeReview cr
		where cr.member.email = :email
		""")
	void deleteAllByEmail(@NonNull String email);

	@Query("""
		select cr from CodeReview cr
				join fetch cr.member
				join fetch cr.codeLanguageCategory
				left join fetch cr.comments
				where cr.member.email = :email
		""")
	List<CodeReview> findByMemberEmail(@NonNull String email);
}
