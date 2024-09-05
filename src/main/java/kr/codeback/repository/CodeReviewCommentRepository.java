package kr.codeback.repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;

@Repository
public interface CodeReviewCommentRepository extends JpaRepository<CodeReviewComment, UUID> {

	List<CodeReviewComment> findByMember(Member member);

	@Query(value = """
		SELECT
		    jcrc.yearMonth AS yearMonth,
		    COUNT(jcrc.id) AS count
		FROM
		    (SELECT
		         IF(crc.create_date IS NOT NULL,
		            DATE_FORMAT(crc.create_date, '%Y%m'),
		            IF(MONTH(:searchDate) >= m.month,
		               CONCAT(YEAR(:searchDate), LPAD(m.month, 2, '0')),
		               CONCAT(YEAR(:searchDate) - 1, LPAD(m.month, 2, '0'))
		            )
		         ) AS yearMonth,
		         crc.id
		     FROM
		         (SELECT 1 AS month UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
		          UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12
		         ) m
		             LEFT JOIN
		         code_review_comment crc
		         ON
		             m.month = MONTH(crc.create_date)
		                 AND crc.create_date BETWEEN DATE_SUB(:searchDate, INTERVAL 5 MONTH) AND :searchDate
		    ) jcrc
		WHERE
		    jcrc.yearMonth BETWEEN DATE_FORMAT(DATE_SUB(:searchDate, INTERVAL 5 MONTH), '%Y%m') AND DATE_FORMAT(:searchDate, '%Y%m')
		GROUP BY
		    jcrc.yearMonth
		ORDER BY
		    jcrc.yearMonth DESC;
		""", nativeQuery = true)
	List<Object[]> calculateSummaryByMonth(Date searchDate);
}
