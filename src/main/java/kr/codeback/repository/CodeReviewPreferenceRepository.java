package kr.codeback.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.CodeReviewPreference;
import kr.codeback.model.entity.Member;
import lombok.NonNull;

@Repository
public interface CodeReviewPreferenceRepository extends JpaRepository<CodeReviewPreference, UUID> {

	List<CodeReviewPreference> findByEntityID(@NonNull UUID EntityId);

	List<CodeReviewPreference> findByMember(@NonNull Member member);
}
