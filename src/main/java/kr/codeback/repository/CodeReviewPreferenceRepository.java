package kr.codeback.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.CodeReviewPreference;

@Repository
public interface CodeReviewPreferenceRepository extends JpaRepository<CodeReviewPreference, UUID> {
}
