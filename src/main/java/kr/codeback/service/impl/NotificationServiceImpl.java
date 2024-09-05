package kr.codeback.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.entity.CodeReviewComment;
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
	public void save(CodeReviewComment codeReviewComment) {
		Notification notification = Notification.builder()
			.id(UUID.randomUUID())
			.member(codeReviewComment.getCodeReview().getMember())
			.entityID(codeReviewComment.getId())
			.isRead(false)
			.message(codeReviewComment.getMember().getNickname()+"님이 "+codeReviewComment.getCodeReview().getTitle()+" 글에 댓글을 작성하였습니다.")
			.build();

		notificationRepository.save(notification);
	}

	@Override
	public List<Notification> getAllNotifications() {
		return notificationRepository.findAll();
	}

	@Override
	public Notification getNotificationById(UUID id) {
		return notificationRepository.findById(id).get();
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

	public void addNotification(Notification notification) {
		if (notificationRepository.findById(notification.getId()).isEmpty()) {
			notificationRepository.save(notification);
		}
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

	@Override
	public void deleteAll(List<Notification> notifications) {
		if (notifications.isEmpty()) {
			return;
		}
		notificationRepository.deleteAll(notifications);
	}

	@Override
	public void update(Notification notification) {
		if (notification != null) {
			notificationRepository.save(notification);
		}
	}

	@Override
	public void markAsRead(Notification notification) {
		notification.hasRead();
	}

	@Override
	public void markAll(){

		List<Notification> notifications = notificationRepository.findAll();

		for (Notification notification : notifications) {
			notification.hasRead();
		}
	}

}
