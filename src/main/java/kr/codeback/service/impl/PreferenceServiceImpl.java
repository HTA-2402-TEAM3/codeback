package kr.codeback.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.codeback.model.dto.response.summary.CodeReviewPreferenceSummaryResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Preference;
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
	public int getCount(UUID entityId) {
		return preferenceRepository.calculateLikeCountByEntityID(entityId);
	}

	@Override
	public Preference save(Member member, String inputEntityId) {
		// inputEntityId 타입 변경
		UUID entityId = UUID.fromString(inputEntityId);

		//Optional은 존재 할수도 있고 존재하지 않을수도 있으면서 null을 처리함
		Optional<Preference> optionalPreference = preferenceRepository.findByMemberAndEntityID(member, entityId);

		// ID가 있어서 like 타입을 변경
		if (optionalPreference.isPresent()) {
			Preference preference = optionalPreference.get();
			preference.changeLike();
			preferenceRepository.save(preference);
			return preference;

			// ID가 없으니 새로생성 함
		} else {
			Preference newPreference = Preference.builder()
				.id(UUID.randomUUID())
				.member(member)
				.entityID(entityId)
				.isLike(true)
				.build();
			preferenceRepository.save(newPreference);
			return newPreference;
		}
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
	public List<Preference> countByEntityIDs(UUID id) {
		Optional<Preference> optionalCodeReviewPreference = preferenceRepository.findById(id);

		return optionalCodeReviewPreference.map(Collections::singletonList).orElseGet(Collections::emptyList);
		//		댓글 없으면 빈 객체 return
	}

	@Override
	@Transactional
	public void deleteAll(List<Preference> preferences) {
		if (preferences.isEmpty()) {
			return;
		}
		preferenceRepository.deleteAll(preferences);
	}

	@Override
	public List<CodeReviewPreferenceSummaryResponseDTO> calculateSummaryByMonth(String inputDate) {

		Date searchDate;
		if (inputDate == null || inputDate.isEmpty()) {
			searchDate = Date.valueOf(LocalDate.now());
		} else {
			searchDate = Date.valueOf(LocalDate.parse(inputDate));
		}

		List<Object[]> results = preferenceRepository.calculateSummaryByMonth(searchDate);

		return results.stream()
			.map(row -> new CodeReviewPreferenceSummaryResponseDTO(Integer.parseInt(row[0].toString()),
				((Number)row[1]).longValue()))
			.toList();
	}

	@Override
	public Map<UUID, Long> countByEntityIDs(List<UUID> reviewIds) {

		List<Object[]> results = preferenceRepository.countByEntityIDs(reviewIds);

		return results.stream().collect(Collectors.toMap(
			result -> (UUID)result[0],
			result -> ((Number)result[1]).longValue()
		));

	}

}
