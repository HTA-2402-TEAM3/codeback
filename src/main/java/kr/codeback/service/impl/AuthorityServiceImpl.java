package kr.codeback.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.AuthorityRepository;
import kr.codeback.service.interfaces.AuthorityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

	private final AuthorityRepository authorityRepository;

	@Override
	public Optional<Authority> findByName(String name) {
		return authorityRepository.findByName(name);
	}
}
