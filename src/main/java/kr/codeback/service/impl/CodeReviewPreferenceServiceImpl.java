package kr.codeback.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.codeback.repository.CodeReviewPreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import kr.codeback.model.entity.CodeReviewPreference;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;

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
	public List<CodeReviewPreference> findById(UUID id) {
		Optional<CodeReviewPreference> optionalCodeReviewPreference =
				codeReviewPreferenceRepository.findById(id);

		return optionalCodeReviewPreference.map(Collections::singletonList)
				.orElseGet(Collections::emptyList);
//		댓글 없으면 빈 객체 return
	}
}
