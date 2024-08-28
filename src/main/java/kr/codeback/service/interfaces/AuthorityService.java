package kr.codeback.service.interfaces;

import java.util.Optional;

import kr.codeback.model.entity.Authority;

public interface AuthorityService {
	Authority findByName(String name);
}
