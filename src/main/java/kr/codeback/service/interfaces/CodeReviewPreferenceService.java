package kr.codeback.service.interfaces;

import java.util.Optional;

import kr.codeback.model.entity.CodeReviewPreference;

public interface CodeReviewPreferenceService {

	// 좋아요 추가
	Optional<CodeReviewPreference> addPreference(String email, String entityId);

	// 좋아요 제거
	Boolean removePreference(String email, String entityId);

	//이메일로 삭제 삭제
	void deleteAllByEmail(String deleteEmail);

}