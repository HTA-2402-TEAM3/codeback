package kr.codeback.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.CodeReviewPreference;
import kr.codeback.model.entity.Member;
import lombok.NonNull;

@Repository
public interface CodeReviewPreferenceRepository extends JpaRepository<CodeReviewPreference, UUID> {

	List<CodeReviewPreference> findByEntityID(@NonNull UUID EntityId);

	List<CodeReviewPreference> findByMember(@NonNull Member member);

	@Query(value = """
		select m.month                       month,
		       COALESCE(COUNT(crp.id), 0) AS count
		from months m
		         left join code_review_preference crp
		                   on m.month = month(crp.create_date)
		                       and current_date > crp.create_date
		                       and crp.create_date >= DATE_SUB(create_date, INTERVAL 5 MONTH)
		where m.month BETWEEN month(DATE_SUB(CURRENT_DATE, INTERVAL 5 MONTH)) AND month(CURRENT_DATE)
		group by m.month
		order by m.month desc
		""", nativeQuery = true)
	List<Object[]> calculateSummaryByMonth();

}
