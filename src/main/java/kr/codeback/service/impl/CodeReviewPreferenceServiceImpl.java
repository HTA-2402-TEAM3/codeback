package kr.codeback.service.impl;

import java.util.Optional;
import java.util.UUID;

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
	public void deleteByEmail(String deleteEmail) {
		codeReviewPreferenceRepository.deleteByEmail(deleteEmail);
	}

	@Override
	@Transactional
	public void deleteByEntityID(UUID entityID) {
		codeReviewPreferenceRepository.deleteByEntityID(entityID);
	}
}
