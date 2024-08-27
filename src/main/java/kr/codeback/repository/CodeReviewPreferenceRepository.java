package kr.codeback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.CodeReviewPreference;
import lombok.NonNull;

@Repository
public interface CodeReviewPreferenceRepository extends JpaRepository<CodeReviewPreference, UUID> {

	@Modifying
	@Query("""
		delete from CodeReviewPreference p
		where p.member.email = :email
		""")
	void deleteByEmail(@NonNull String email);

	@Modifying
	@Query("""
		delete from CodeReviewPreference p
		where p.entityID = :entityID
		""")
	void deleteByEntityID(@NonNull UUID entityID);
}
