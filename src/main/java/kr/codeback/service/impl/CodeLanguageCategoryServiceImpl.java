package kr.codeback.service.impl;

import kr.codeback.model.entity.CodeLanguageCategory;
import kr.codeback.repository.CodeLanguageCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import kr.codeback.service.interfaces.CodeLanguageCategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeLanguageCategoryServiceImpl implements CodeLanguageCategoryService {

    private final CodeLanguageCategoryRepository codeLanguageCategoryRepository;

    @Override
    public List<CodeLanguageCategory> findAll() {
        return codeLanguageCategoryRepository.findAll();
    }
}
