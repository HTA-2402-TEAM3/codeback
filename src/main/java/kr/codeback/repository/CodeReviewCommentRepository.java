package kr.codeback.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.dto.response.summary.CodeReviewCommentSummaryResponseDTO;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;

@Repository
public interface CodeReviewCommentRepository extends JpaRepository<CodeReviewComment, UUID> {

	List<CodeReviewComment> findByMember(Member member);

	@Query(value = """
		SELECT m.month                       month,
		       COALESCE(COUNT(crc.id), 0) AS count
		FROM months m
		         LEFT JOIN
		     code_review_comment crc
		     ON
		         month(crc.create_date) = m.month
		             and current_date > crc.create_date
		             and crc.create_date >= DATE_SUB(create_date, INTERVAL 5 MONTH)
		where m.month BETWEEN month(DATE_SUB(CURRENT_DATE, INTERVAL 5 MONTH)) AND month(CURRENT_DATE)
		GROUP BY m.month
		ORDER BY m.month DESC
		""", nativeQuery = true)
	List<Object[]> calculateSummaryByMonth();
}
