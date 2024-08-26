package kr.codeback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.codeback.model.entity.Notification;
import lombok.NonNull;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

	@Modifying
	@Query("""
		delete from Notification n
		where n.member.email = :email
		""")
	void deleteAllByEmail(@NonNull String email);

}
