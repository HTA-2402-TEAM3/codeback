package kr.codeback.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Notification;
import lombok.NonNull;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

	List<Notification> findByMember(@NonNull Member member);

	List<Notification> findByEntityID(@NonNull UUID entityID);
}
