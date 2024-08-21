package kr.codeback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
}
