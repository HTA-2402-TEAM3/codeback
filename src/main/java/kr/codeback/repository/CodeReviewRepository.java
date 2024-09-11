package kr.codeback.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.dto.response.summary.CodeReviewSummaryByLanguageResponseDTO;
import kr.codeback.model.entity.CodeReview;
import lombok.NonNull;

@Repository
public interface CodeReviewRepository extends JpaRepository<CodeReview, UUID>, JpaSpecificationExecutor<CodeReview> {

	@Query("""
		select cr from CodeReview cr
		join fetch cr.member
		join fetch cr.codeLanguageCategory
		left join fetch cr.comments
		where cr.id = :id
		""")
	Optional<CodeReview> findByIdWithComments(@NonNull UUID id);

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
		SELECT
		    jcrp.yearMonth AS yearMonth,
		    COUNT(jcrp.id) AS count
		FROM
		    (SELECT
		         IF(cr.create_date IS NOT NULL,
		            DATE_FORMAT(cr.create_date, '%Y%m'),
		            IF(MONTH(:searchDate) >= m.month,
		               CONCAT(YEAR(:searchDate), LPAD(m.month, 2, '0')),
		               CONCAT(YEAR(:searchDate) - 1, LPAD(m.month, 2, '0'))
		            )
		         ) AS yearMonth,
		         cr.id
		     FROM
		         (SELECT 1 AS month UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
		          UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12
		         ) m
		             LEFT JOIN
		         code_review cr
		         ON
		             m.month = MONTH(cr.create_date)
		                 AND cr.create_date BETWEEN DATE_SUB(:searchDate, INTERVAL 5 MONTH) AND :searchDate
		    ) jcrp
		WHERE
		    jcrp.yearMonth BETWEEN DATE_FORMAT(DATE_SUB(:searchDate, INTERVAL 5 MONTH), '%Y%m') AND DATE_FORMAT(:searchDate, '%Y%m')
		GROUP BY
		    jcrp.yearMonth
		ORDER BY
		    jcrp.yearMonth
		""", nativeQuery = true)
	List<Object[]> calculateSummaryByMonth(Date searchDate);

}
