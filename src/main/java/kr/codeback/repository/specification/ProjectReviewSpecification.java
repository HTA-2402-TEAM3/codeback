package kr.codeback.repository.specification;

import jakarta.persistence.criteria.JoinType;
import kr.codeback.model.entity.ProjectReview;
import org.springframework.data.jpa.domain.Specification;

public class ProjectReviewSpecification {
    public static Specification<ProjectReview> hasKeyword(String search) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);

            if(search == null || search.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            var memberJoin = root.join("member", JoinType.LEFT);
            var tagJoin = root.join("projectReviewTags", JoinType.LEFT);

            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("title"), "%"+search+"%"),
                    criteriaBuilder.like(root.get("content"), "%" + search + "%"),
                    criteriaBuilder.like(memberJoin.get("nickname"), "%" + search + "%"),
                    criteriaBuilder.like(tagJoin.get("tag"), "%" + search + "%")
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
