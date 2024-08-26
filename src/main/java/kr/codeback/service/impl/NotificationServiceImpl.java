package kr.codeback.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import kr.codeback.model.entity.Notification;
import kr.codeback.repository.NotificationRepository;
import kr.codeback.service.interfaces.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

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
}
