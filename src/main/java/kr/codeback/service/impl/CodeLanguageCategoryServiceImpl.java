package kr.codeback.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.codeback.model.entity.CodeLanguageCategory;
import kr.codeback.repository.CodeLanguageCategoryRepository;
import kr.codeback.service.interfaces.CodeLanguageCategoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeLanguageCategoryServiceImpl implements CodeLanguageCategoryService {

	private final CodeLanguageCategoryRepository codeLanguageCategoryRepository;

	@Override
	public List<CodeLanguageCategory> findAll() {
		return codeLanguageCategoryRepository.findAll();
	}
}
