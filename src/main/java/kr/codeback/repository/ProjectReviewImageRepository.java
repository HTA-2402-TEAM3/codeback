package kr.codeback.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.ProjectReviewImage;

@Repository
public interface ProjectReviewImageRepository extends JpaRepository<ProjectReviewImage, UUID> {

	void deleteAllByProjectReviewId(UUID projectReviewId);

	Set<ProjectReviewImage> findAllByProjectReviewId(UUID projectReviewId);
}
