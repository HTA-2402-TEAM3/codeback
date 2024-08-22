package kr.codeback.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.CodeReview;

@Repository
public interface CodeReviewRepository extends JpaRepository<CodeReview, UUID> {

	@Override
	@Query("""
		select cr from CodeReview cr
		join fetch cr.member
		join fetch cr.codeLanguageCategory
		join fetch cr.comments
		where cr.id = :id
		""")
	Optional<CodeReview> findById(UUID id);
}
