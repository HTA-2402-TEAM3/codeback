package kr.codeback.service.interfaces;

import java.util.Optional;
import java.util.UUID;

import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.Member;

public interface AuthorityService {
	Optional<Authority> findByName(String name);
}
