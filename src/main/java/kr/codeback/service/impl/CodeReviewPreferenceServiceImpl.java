package kr.codeback.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.codeback.model.entity.CodeReviewPreference;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;

@Service
public class CodeReviewPreferenceServiceImpl implements CodeReviewPreferenceService {
	@Override
	public Optional<CodeReviewPreference> addPreference(String email, String entityId) {
		return Optional.empty();
	}

	@Override
	public Boolean removePreference(String email, String entityId) {
		return null;
	}
}
