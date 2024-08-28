package kr.codeback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
