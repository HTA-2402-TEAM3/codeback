package kr.codeback.service.impl;

import java.util.Collections;
import java.util.Comparator;
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
	@Transactional
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
	public Notification getNotificationById(UUID id) {
		return notificationRepository.findById(id).get();
	}


	@Override
	@Transactional
	public void deleteByMember(Member member) {
		List<Notification> notifications = getNotifications(member);
		if (notifications.isEmpty()) {
			return;
		}
		notificationRepository.deleteAll(notifications);
	}


	@Override
	public List<Notification> getNotifications(Member member) {
		List<Notification> notifications = notificationRepository.findAllByMember(member);
		Collections.sort(notifications, Comparator.comparing(Notification::getCreateDate).reversed());
		return notifications;

	}

	@Override
	@Transactional
	public List<Notification> findByEntityID(UUID entityID) {
		return notificationRepository.findByEntityID(entityID);
	}

	@Override
	@Transactional
	public void deleteAll(Member member) {

		List<Notification> notifications = notificationRepository.findAllByMember(member);
		notifications.forEach(notification -> delete(notification.getId()));

	}

	@Override
	@Transactional
	public void deleteByEntityId(UUID entityId){

		List<Notification> notifications = notificationRepository.findByEntityID(entityId);
		notifications.forEach(notification -> delete(notification.getId()));
	}

	@Override
	public void update(Notification notification) {
		if (notification != null) {
			notificationRepository.save(notification);
		}
	}

	@Override
	@Transactional
	public void markAsRead(Notification notification) {
		notification.hasRead();
		update(notification);
	}

	@Override
	public void delete(UUID id) {
		notificationRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void markAll(Member member){

		List<Notification> notifications = notificationRepository.findAllByMember(member);
		notifications.forEach(this::markAsRead);

	}

}
