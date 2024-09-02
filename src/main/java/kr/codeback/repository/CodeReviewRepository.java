package kr.codeback.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.dto.response.summary.CodeReviewSummaryByLanguageResponseDTO;
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

	Page<CodeReview> findByCodeLanguageCategoryId(UUID language, Pageable pageable);

	@Query("""
		select
		new kr.codeback.model.dto.response.summary.CodeReviewSummaryByLanguageResponseDTO(
		cl.languageName,
		count(*)
		)
		from CodeReview c
		join CodeLanguageCategory cl
		on c.codeLanguageCategory.id = cl.id
		group by cl.languageName
		""")
	List<CodeReviewSummaryByLanguageResponseDTO> calculateSummaryByLanguage();

	@Query(value = """
		SELECT m.month as month,
		       coalesce(count(cr.id), 0) as count
		FROM months m
		         left join code_review cr
		                   on m.month = month(cr.create_date)
		                       and current_date > cr.create_date
		                       and cr.create_date >= date_sub(current_date, interval 5 month)
		WHERE m.month between month(date_sub(current_date, interval 5 month)) and month(current_date)
		GROUP BY m.month
		ORDER BY m.month desc
		""", nativeQuery = true)
	List<Object[]> calculateSummaryByWeek();

}
