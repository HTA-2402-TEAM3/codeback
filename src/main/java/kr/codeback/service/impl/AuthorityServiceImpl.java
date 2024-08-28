package kr.codeback.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
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
		return authorityRepository.findByName(name)
			.orElseThrow(() -> new IllegalArgumentException("Authority not found"));
	}
}
