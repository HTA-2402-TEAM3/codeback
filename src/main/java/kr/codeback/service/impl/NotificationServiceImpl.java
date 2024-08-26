package kr.codeback.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.entity.Notification;
import kr.codeback.repository.NotificationRepository;
import kr.codeback.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;

	@Override
	public ArrayList<Notification> getAllNotifications() {
		return null;
	}

	@Override
	public ArrayList<Notification> getNotificationsByEmail(String email) {
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
	public void deleteAllByEmail(String deleteEmail) {
		notificationRepository.deleteAllByEmail(deleteEmail);
	}
}
