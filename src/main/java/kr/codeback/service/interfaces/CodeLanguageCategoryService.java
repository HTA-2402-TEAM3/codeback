package kr.codeback.service.interfaces;

import kr.codeback.model.entity.CodeLanguageCategory;

import java.util.List;

public interface CodeLanguageCategoryService {
    List<CodeLanguageCategory> findAll();
}
