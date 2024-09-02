package kr.codeback.service.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.codeback.model.dto.response.summary.CodeReviewPreferenceSummaryResponseDTO;
import kr.codeback.model.entity.CodeReviewPreference;
import kr.codeback.model.entity.Member;

public interface CodeReviewPreferenceService {

	// 좋아요 추가
	Optional<CodeReviewPreference> addPreference(String email, String entityId);

	// 좋아요 제거
	Boolean removePreference(String email, String entityId);

	void deleteByMember(Member member);

	void deleteByEntityID(UUID entityID);

	List<CodeReviewPreference> findByEntityID(UUID entityID);

	List<CodeReviewPreference> findByMember(Member member);

	List<CodeReviewPreference> findById(UUID id);

	List<CodeReviewPreferenceSummaryResponseDTO> calculateSummaryByMonth();
}