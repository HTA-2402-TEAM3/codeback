package kr.codeback.repository.specification;

import kr.codeback.model.entity.CodeReview;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class CodeReviewSpecification {
    public static Specification<CodeReview> hasLanguage(UUID codeLanguageId) {
        return (root, query, criteriaBuilder) -> {
            if (codeLanguageId == null) {
                return criteriaBuilder.conjunction();
//                조건 없으면 필터링 x
            }
            return criteriaBuilder.equal(root.get("codeLanguageCategory").get("id"), codeLanguageId);
        };
    }

    public static Specification<CodeReview> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return criteriaBuilder.conjunction();
//            조건 없으면 필터링 x
            }
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("title"), "%" + keyword + "%"),
                    criteriaBuilder.like(root.get("content"), "%" + keyword + "%"),
                    criteriaBuilder.like(root.get("member").get("nickname"), "%" + keyword + "%")
            );
        };
    }
}