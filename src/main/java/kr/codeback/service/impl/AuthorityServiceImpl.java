package kr.codeback.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import kr.codeback.model.entity.Authority;
import kr.codeback.repository.AuthorityRepository;
import kr.codeback.service.interfaces.AuthorityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

	private final AuthorityRepository authorityRepository;

	@Override
	public Authority findByName(String name) {

		new ArrayList<>();

		return authorityRepository.findByName(name)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 권한입니다."));
	}
}
