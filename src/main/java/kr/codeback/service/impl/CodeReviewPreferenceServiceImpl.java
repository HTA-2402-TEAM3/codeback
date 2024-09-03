package kr.codeback.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.dto.response.summary.CodeReviewPreferenceSummaryResponseDTO;
import kr.codeback.model.entity.CodeReviewPreference;
import kr.codeback.model.entity.Member;
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
	public List<CodeReviewPreference> findByMember(Member member) {
		return codeReviewPreferenceRepository.findByMember(member);
	}

	@Override
	@Transactional
	public void deleteByMember(Member member) {

		List<CodeReviewPreference> codeReviewPreferences = findByMember(member);

		if (codeReviewPreferences.isEmpty()) {
			return;
		}

		codeReviewPreferenceRepository.deleteAll(codeReviewPreferences);
	}

	@Override
	public List<CodeReviewPreference> findByEntityID(UUID entityID) {
		return codeReviewPreferenceRepository.findByEntityID(entityID);
	}

	@Override
	@Transactional
	public void deleteByEntityID(UUID entityID) {

		List<CodeReviewPreference> codeReviewPreferences = findByEntityID(entityID);

		if (codeReviewPreferences.isEmpty()) {
			return;
		}

		codeReviewPreferenceRepository.deleteAll(codeReviewPreferences);
	}

	@Override
	public List<CodeReviewPreference> findById(UUID id) {
		Optional<CodeReviewPreference> optionalCodeReviewPreference =
			codeReviewPreferenceRepository.findById(id);

		return optionalCodeReviewPreference.map(Collections::singletonList)
			.orElseGet(Collections::emptyList);
		//		댓글 없으면 빈 객체 return
	}

	@Override
	public void deleteAll(List<CodeReviewPreference> preferences) {
		if(preferences.isEmpty()) {
			return;
		}
		codeReviewPreferenceRepository.deleteAll(preferences);
	}
	@Override
	public List<CodeReviewPreferenceSummaryResponseDTO> calculateSummaryByMonth(String inputDate) {


		Date searchDate = null;
		if (inputDate == null || inputDate.isEmpty()) {
			searchDate = Date.valueOf(LocalDate.now());
		} else {
			searchDate = Date.valueOf(LocalDate.parse(inputDate));
		}

		List<Object[]> results = codeReviewPreferenceRepository.calculateSummaryByMonth(searchDate);

		return results.stream().map(row ->
				new CodeReviewPreferenceSummaryResponseDTO(
					Integer.parseInt(row[0].toString()),
					((Number)row[1]).longValue()
				))
			.toList();
	}

}
