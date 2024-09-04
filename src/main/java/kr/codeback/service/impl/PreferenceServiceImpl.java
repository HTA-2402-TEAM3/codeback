package kr.codeback.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.codeback.model.entity.Preference;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.dto.response.summary.CodeReviewPreferenceSummaryResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.PreferenceRepository;
import kr.codeback.service.interfaces.PreferenceService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreferenceServiceImpl implements PreferenceService {

	private final PreferenceRepository preferenceRepository;

	@Override
	public Optional<Preference> addPreference(String email, String entityId) {
		return Optional.empty();
	}

	@Override
	public Boolean removePreference(String email, String entityId) {
		return null;
	}

	@Override
	public List<Preference> findByMember(Member member) {
		return preferenceRepository.findByMember(member);
	}

	@Override
	@Transactional
	public void deleteByMember(Member member) {

		List<Preference> preferences = findByMember(member);

		if (preferences.isEmpty()) {
			return;
		}

		preferenceRepository.deleteAll(preferences);
	}

	@Override
	public List<Preference> findByEntityID(UUID entityID) {
		return preferenceRepository.findByEntityID(entityID);
	}

	@Override
	@Transactional
	public void deleteByEntityID(UUID entityID) {

		List<Preference> preferences = findByEntityID(entityID);

		if (preferences.isEmpty()) {
			return;
		}

		preferenceRepository.deleteAll(preferences);
	}

	@Override
	public List<Preference>  findById(UUID id) {
		Optional<Preference> optionalCodeReviewPreference =
			preferenceRepository.findById(id);

		return optionalCodeReviewPreference.map(Collections::singletonList)
			.orElseGet(Collections::emptyList);
		//		댓글 없으면 빈 객체 return
	}

	@Override
	public void deleteAll(List<Preference> preferences) {
		if(preferences.isEmpty()) {
			return;
		}
		preferenceRepository.deleteAll(preferences);
	}
	@Override
	public List<CodeReviewPreferenceSummaryResponseDTO> calculateSummaryByMonth(String inputDate) {


		Date searchDate = null;
		if (inputDate == null || inputDate.isEmpty()) {
			searchDate = Date.valueOf(LocalDate.now());
		} else {
			searchDate = Date.valueOf(LocalDate.parse(inputDate));
		}

		List<Object[]> results = preferenceRepository.calculateSummaryByMonth(searchDate);

		return results.stream().map(row ->
				new CodeReviewPreferenceSummaryResponseDTO(
					Integer.parseInt(row[0].toString()),
					((Number)row[1]).longValue()
				))
			.toList();
	}

}
