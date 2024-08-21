package kr.codeback.service;

import java.util.ArrayList;
import java.util.Optional;

import kr.codeback.model.entity.Preference;

public interface PreferenceService {

	// 좋아요 추가
	Optional<Preference> addPreference(String email, String entityId);

	// 좋아요 제거
	Boolean removePreference(String email, String entityId);

}