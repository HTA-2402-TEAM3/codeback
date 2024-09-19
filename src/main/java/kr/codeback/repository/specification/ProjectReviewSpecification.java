package kr.codeback.repository.specification;

import kr.codeback.model.entity.ProjectReview;
import org.springframework.data.jpa.domain.Specification;

public class ProjectReviewSpecification {
    public static Specification<ProjectReview> hasKeyword(String search) {
        return (root, query, criteriaBuilder) -> {
            if(search == null || search.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("title"), "%"+search+"%"),
                    criteriaBuilder.like(root.get("content"), "%" + search + "%"),
                    criteriaBuilder.like(root.get("member").get("nickname"), "%" + search + "%"),
                    criteriaBuilder.like(root.get("projectReviewTags").get("tag"), "%" + search + "%")
            );
        };
    }

    public static Specification<ProjectReview> hasTag(String tag) {
        return (root, query, criteriaBuilder) -> {
            if(tag == null || tag.isEmpty()) {
                return criteriaBuilder.conjunction();
            } return criteriaBuilder.like(root.get("projectReviewTags").get("tag"), tag);
        };
    }
}
