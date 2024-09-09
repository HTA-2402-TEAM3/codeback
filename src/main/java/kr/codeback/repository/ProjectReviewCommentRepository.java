package kr.codeback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.ProjectReviewComment;

@Repository
public interface ProjectReviewCommentRepository extends JpaRepository<ProjectReviewComment, UUID> {

	void deleteAllByProjectReviewId(UUID projectReviewId);
}
