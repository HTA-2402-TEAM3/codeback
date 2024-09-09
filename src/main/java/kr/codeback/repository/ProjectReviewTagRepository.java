package kr.codeback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.ProjectReviewTag;

@Repository
public interface ProjectReviewTagRepository extends JpaRepository<ProjectReviewTag, UUID> {

	public void deleteAllByProjectReviewId(UUID id);
}
