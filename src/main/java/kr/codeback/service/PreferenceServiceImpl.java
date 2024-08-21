package kr.codeback.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.codeback.model.entity.Preference;

@Service
public class PreferenceServiceImpl implements PreferenceService {
	@Override
	public Optional<Preference> addPreference(String email, String entityId) {
		return Optional.empty();
	}

	@Override
	public Boolean removePreference(String email, String entityId) {
		return null;
	}
}
