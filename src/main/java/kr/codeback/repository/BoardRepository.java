package kr.codeback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {

}
