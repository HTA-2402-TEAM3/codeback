package kr.codeback.service.interfaces;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import kr.codeback.model.entity.Notification;

public interface NotificationService {

	// 모든 알림 조회
	ArrayList<Notification> getAllNotifications();

	// 특정 사용자 이메일로 알림 조회
	ArrayList<Notification> getNotificationsByEmail(String email);

	// 알림 저장
	Optional<Notification> createNotification(Notification notification);

	// 알림 읽음 처리 (이건 구현할 때 수정해야할 것 같습니다!)
	Notification markNotificationAsRead(Long notificationId);

	// 알림 삭제
	Boolean deleteNotification(UUID notificationId);

	// 이메일로 삭제
	void deleteAllByEmail(String deleteEmail);

}
