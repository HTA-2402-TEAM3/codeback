package kr.codeback.repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.ProjectReview;

@Repository
public interface ProjectReviewRepository extends JpaRepository<ProjectReview, UUID>, JpaSpecificationExecutor<ProjectReview> {

	@Query(value = """
		SELECT
		    jpr.yearMonth AS yearMonth,
		    COUNT(jpr.id) AS count
		FROM
		    (SELECT
		         IF(pr.create_date IS NOT NULL,
		            DATE_FORMAT(pr.create_date, '%Y%m'),
		            IF(MONTH(:searchDate) >= m.month,
		               CONCAT(YEAR(:searchDate), LPAD(m.month, 2, '0')),
		               CONCAT(YEAR(:searchDate) - 1, LPAD(m.month, 2, '0'))
		            )
		         ) AS yearMonth,
		         pr.id
		     FROM
		         (SELECT 1 AS month UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
		          UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12
		         ) m
		             LEFT JOIN
		         project_review pr
		         ON
		             m.month = MONTH(pr.create_date)
		                 AND pr.create_date BETWEEN DATE_SUB(:searchDate, INTERVAL 5 MONTH) AND :searchDate
		    ) jpr
		WHERE
		    jpr.yearMonth BETWEEN DATE_FORMAT(DATE_SUB(:searchDate, INTERVAL 5 MONTH), '%Y%m') AND DATE_FORMAT(:searchDate, '%Y%m')
		GROUP BY
		    jpr.yearMonth
		ORDER BY
		    jpr.yearMonth
		""", nativeQuery = true)
	List<Object[]> calculateSummaryByMonth(Date searchDate);

}
