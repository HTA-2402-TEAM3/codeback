package kr.codeback.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import kr.codeback.model.entity.CodeReview;
import lombok.NonNull;

@Repository
public interface CodeReviewRepository extends JpaRepository<CodeReview, UUID> {

	@Query("""
		select cr from CodeReview cr
		join fetch cr.member
		join fetch cr.codeLanguageCategory
		left join fetch cr.comments
		where cr.id = :id
		""")
	Optional<CodeReview> findByIdWithComments(@NonNull UUID id);

  //	댓글이 없을 때도 게시글 조회 -> left join
	@Query("""
		select cr from CodeReview cr
				join fetch cr.member
				join fetch cr.codeLanguageCategory
				left join fetch cr.comments
				where cr.member.email = :email
		""")
	List<CodeReview> findByMemberEmail(@NonNull String email);

	Optional<CodeReview> findById(UUID id);

	Page<CodeReview> findByCodeLanguageCategoryID(UUID language, Pageable pageable);

}
