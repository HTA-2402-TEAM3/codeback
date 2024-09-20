package kr.codeback.repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.ProjectReviewComment;

@Repository
public interface ProjectReviewCommentRepository extends JpaRepository<ProjectReviewComment, UUID> {

	void deleteAllByProjectReviewId(UUID projectReviewId);


	@Query(value = """
		SELECT
		    pcrc.yearMonth AS yearMonth,
		    COUNT(pcrc.id) AS count
		FROM
		    (SELECT
		         IF(prc.create_date IS NOT NULL,
		            DATE_FORMAT(prc.create_date, '%Y%m'),
		            IF(MONTH(:searchDate) >= m.month,
		               CONCAT(YEAR(:searchDate), LPAD(m.month, 2, '0')),
		               CONCAT(YEAR(:searchDate) - 1, LPAD(m.month, 2, '0'))
		            )
		         ) AS yearMonth,
		         prc.id
		     FROM
		         (SELECT 1 AS month UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
		          UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12
		         ) m
		             LEFT JOIN
		         project_review_comment prc
		         ON
		             m.month = MONTH(prc.create_date)
		                 AND prc.create_date BETWEEN DATE_SUB(:searchDate, INTERVAL 5 MONTH) AND :searchDate
		    ) pcrc
		WHERE
		    pcrc.yearMonth BETWEEN DATE_FORMAT(DATE_SUB(:searchDate, INTERVAL 5 MONTH), '%Y%m') AND DATE_FORMAT(:searchDate, '%Y%m')
		GROUP BY
		    pcrc.yearMonth
		ORDER BY
		    pcrc.yearMonth;
		""", nativeQuery = true)
	List<Object[]> calculateSummaryByMonth(Date searchDate);

}
