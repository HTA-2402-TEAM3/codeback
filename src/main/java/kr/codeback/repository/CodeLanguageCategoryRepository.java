package kr.codeback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.codeback.model.entity.CodeLanguageCategory;

public interface CodeLanguageCategoryRepository extends JpaRepository<CodeLanguageCategory, UUID> {
}
