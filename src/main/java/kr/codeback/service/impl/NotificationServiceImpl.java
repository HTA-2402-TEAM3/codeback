package kr.codeback.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Notification;
import kr.codeback.model.entity.Preference;
import kr.codeback.model.entity.ProjectReviewComment;
import kr.codeback.repository.CodeReviewCommentRepository;
import kr.codeback.repository.CodeReviewRepository;
import kr.codeback.repository.NotificationRepository;
import kr.codeback.repository.ProjectReviewCommentRepository;
import kr.codeback.repository.ProjectReviewRepository;
import kr.codeback.service.interfaces.CodeReviewService;
import kr.codeback.service.interfaces.NotificationService;
import kr.codeback.service.interfaces.ProjectReviewCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;
	private final CodeReviewRepository codeReviewRepository;
	private final CodeReviewCommentRepository codeReviewCommentRepository;
	private final ProjectReviewRepository projectReviewRepository;
	private final ProjectReviewCommentRepository projectReviewCommentRepository;

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
	@Transactional
	public void save(Preference preference,String type) {

		log.info(String.valueOf(preference.getEntityID()));

		Notification notification = Notification.builder()
			.id(UUID.randomUUID())
			.member(preference.getMember())
			.entityID(preference.getId())
			.isRead(false)
			.message(generateMessage(preference,type))
			.build();


		log.info("notification message");
		notificationRepository.save(notification);
	}

	@Override
	@Transactional
	public void save(ProjectReviewComment projectReviewComment){
		Notification notification = Notification.builder()
			.id(UUID.randomUUID())
			.member(projectReviewComment.getProjectReview().getMember())
			.entityID(projectReviewComment.getId())
			.isRead(false)
			.message(projectReviewComment.getMember().getNickname()+"님이 "+projectReviewComment.getProjectReview().getTitle()+" 글에 댓글을 작성하였습니다.")
			.build();

		notificationRepository.save(notification);

	}

	public String generateMessage(Preference preference, String type){
		String message = preference.getMember().getNickname()+"님이 ";
		if(type.equals("codeReview")){
			log.info(Objects.requireNonNull(codeReviewRepository.findById(preference.getEntityID()).orElse(null)).toString());
			log.info(message + codeReviewRepository.findById(UUID.fromString(String.valueOf(preference.getEntityID()))).orElse(null)+" 글에 좋아요를 눌렀습니다.");
			return message + codeReviewRepository.findById(preference.getEntityID()).orElse(null).getTitle()+" 글에 좋아요를 눌렀습니다.";
		} else if (type.equals("codeReviewComment")) {
			return message + codeReviewCommentRepository.findById(preference.getEntityID()).orElse(null).getCodeReview().getTitle()+"에 달린 댓글에 좋아요를 눌렀습니다.";
		} else if (type.equals("projectReview")) {
			return message + projectReviewRepository.findById(preference.getEntityID()).orElse(null).getTitle()+" 글에 좋아요를 눌렀습니다.";
		} else if (type.equals("projectReviewComment")) {
			return message + projectReviewCommentRepository.findById(preference.getEntityID()).orElse(null).getProjectReview().getTitle()+"에 달린 댓글에 좋아요를 눌렀습니다.";
		}
		return message;
	}

	@Override
	public Notification getNotificationById(UUID id) {
		return notificationRepository.findById(id).orElse(null);
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
