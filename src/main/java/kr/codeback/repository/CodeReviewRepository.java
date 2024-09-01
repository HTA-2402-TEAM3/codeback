package kr.codeback.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.dto.response.CodeReviewSummaryByLanguageResponseDTO;
import kr.codeback.model.dto.response.CodeReviewSummaryByWeekResponseDTO;
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
		new kr.codeback.model.dto.response.CodeReviewSummaryByLanguageResponseDTO(
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
		    SELECT
		        WEEK(CURRENT_DATE) - WEEK(cr.create_date) AS week_diff,
		        COUNT(*) AS count
		    FROM
		        code_review cr
		    WHERE
		        cr.create_date >= DATE_SUB(CURRENT_DATE, INTERVAL 10 WEEK)
		    GROUP BY
		        WEEK(cr.create_date)
		    ORDER BY
		        week_diff
		""", nativeQuery = true)
	List<Object[]> calculateSummaryByWeek();

}
