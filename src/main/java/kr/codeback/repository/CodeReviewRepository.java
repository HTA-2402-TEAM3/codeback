package kr.codeback.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.codeback.model.dto.response.CodeReviewResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import kr.codeback.model.entity.CodeReview;

@Repository
public interface CodeReviewRepository extends JpaRepository<CodeReview, UUID> {

	@Override
	@Query("""
		select cr from CodeReview cr
		join fetch cr.member
		join fetch cr.codeLanguageCategory
		left join fetch cr.comments
		where cr.id = :id
		""")
//	댓글이 없을 때도 게시글 조회 -> left join
	Optional<CodeReview> findById(UUID id);

	Page<CodeReview> findByCodeLanguageCategory_Id(UUID language, Pageable pageable);

	Optional<CodeReview> findByCodeLanguageCategory_Id(UUID language);
}
