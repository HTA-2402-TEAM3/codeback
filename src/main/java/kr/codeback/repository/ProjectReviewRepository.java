package kr.codeback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.ProjectReview;

@Repository
public interface ProjectReviewRepository extends JpaRepository<ProjectReview, UUID>, JpaSpecificationExecutor<ProjectReview> {

}
