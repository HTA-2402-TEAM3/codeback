package kr.codeback.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Notification;
import kr.codeback.repository.NotificationRepository;
import kr.codeback.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;

	@Override
	public List<Notification> getAllNotifications() {
		return null;
	}

	@Override
	public List<Notification> getNotificationsByEmail(String email) {
		return null;
	}

	@Override
	public Optional<Notification> createNotification(Notification notification) {
		return Optional.empty();
	}

	@Override
	public Notification markNotificationAsRead(Long notificationId) {
		return null;
	}

	@Override
	public Boolean deleteNotification(UUID notificationId) {
		return null;
	}

	@Override
	@Transactional
	public void deleteByMember(Member member) {

		List<Notification> notifications = findByMember(member);

		if (notifications.isEmpty()) {
			return;
		}

		notificationRepository.deleteAll(notifications);
	}

	@Override
	@Transactional
	public void deleteByEntityID(UUID entityID) {

		List<Notification> notifications = findByEntityID(entityID);

		if (notifications.isEmpty()) {
			return;
		}

		notificationRepository.deleteAll(notifications);
	}

	@Override
	public List<Notification> findByMember(Member member) {
		return notificationRepository.findByMember(member);
	}

	@Override
	public List<Notification> findByEntityID(UUID entityID) {
		return notificationRepository.findByEntityID(entityID);
	}

}
