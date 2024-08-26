package kr.codeback.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.entity.CodeReviewPreference;
import kr.codeback.repository.CodeReviewPreferenceRepository;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeReviewPreferenceServiceImpl implements CodeReviewPreferenceService {

	private final CodeReviewPreferenceRepository codeReviewPreferenceRepository;

	@Override
	public Optional<CodeReviewPreference> addPreference(String email, String entityId) {
		return Optional.empty();
	}

	@Override
	public Boolean removePreference(String email, String entityId) {
		return null;
	}

	@Override
	@Transactional
	public void deleteAllByEmail(String deleteEmail) {
		codeReviewPreferenceRepository.deleteAllByEmail(deleteEmail);
	}
}
