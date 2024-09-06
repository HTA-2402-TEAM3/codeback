package kr.codeback.service.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.codeback.model.dto.response.summary.CodeReviewPreferenceSummaryResponseDTO;
import kr.codeback.model.entity.Preference;
import kr.codeback.model.entity.Member;

public interface PreferenceService {

    // 좋아요 추가
    Optional<Preference> addPreference(String email, String entityId);

    int getCount(UUID entityId);

    void save(Member member, String entityId);

    // 좋아요 제거
    Boolean removePreference(String email, String entityId);

    void deleteByMember(Member member);

    void deleteByEntityID(UUID entityID);

    List<Preference> findByEntityID(UUID entityID);

    List<Preference> findByMember(Member member);

    List<Preference> findById(UUID id);

    void deleteAll(List<Preference> preferences);

    List<CodeReviewPreferenceSummaryResponseDTO> calculateSummaryByMonth(String inputDate);
}