package kr.codeback.service.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Notification;

public interface NotificationService {

	// 모든 알림 조회
	List<Notification> getAllNotifications();

	// 특정 사용자 이메일로 알림 조회
	List<Notification> getNotificationsByEmail(String email);

	// 알림 저장
	Optional<Notification> createNotification(Notification notification);

	// 알림 읽음 처리 (이건 구현할 때 수정해야할 것 같습니다!)
	Notification markNotificationAsRead(Long notificationId);

	// 알림 삭제
	Boolean deleteNotification(UUID notificationId);

	void deleteByMember(Member member);

	void deleteByEntityID(UUID entityID);

	List<Notification> findByMember(Member member);

	List<Notification> findByEntityID(UUID entityID);

}
