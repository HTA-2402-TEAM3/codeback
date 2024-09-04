package kr.codeback.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.codeback.model.entity.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.Member;
import lombok.NonNull;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, UUID> {
	Optional<Preference> findByEntityIDAndMember(UUID entityID, Member member);
	List<Preference> findByEntityID(@NonNull UUID EntityId);

	List<Preference> findByMember(@NonNull Member member);

	@Query(value = """
		SELECT
		    jcrp.yearMonth AS yearMonth,
		    COUNT(jcrp.id) AS count
		FROM
		    (SELECT
		         IF(crp.create_date IS NOT NULL,
		            DATE_FORMAT(crp.create_date, '%Y%m'),
		            IF(MONTH(:searchDate) >= m.month,
		               CONCAT(YEAR(:searchDate), LPAD(m.month, 2, '0')),
		               CONCAT(YEAR(:searchDate) - 1, LPAD(m.month, 2, '0'))
		            )
		         ) AS yearMonth,
		         crp.id
		     FROM
		         (SELECT 1 AS month UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
		          UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12
		         ) m
		             LEFT JOIN
		         code_review_preference crp
		         ON
		             m.month = MONTH(crp.create_date)
		                 AND crp.create_date BETWEEN DATE_SUB(:searchDate, INTERVAL 5 MONTH) AND :searchDate
		    ) jcrp
		WHERE
		    jcrp.yearMonth BETWEEN DATE_FORMAT(DATE_SUB(:searchDate, INTERVAL 5 MONTH), '%Y%m') AND DATE_FORMAT(:searchDate, '%Y%m')
		GROUP BY
		    jcrp.yearMonth
		ORDER BY
		    jcrp.yearMonth DESC
		""", nativeQuery = true)
	List<Object[]> calculateSummaryByMonth(Date searchDate);

}
