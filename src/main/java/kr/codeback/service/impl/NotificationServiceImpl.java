package kr.codeback.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Notification;
import kr.codeback.model.entity.Preference;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewComment;
import kr.codeback.repository.CodeReviewCommentRepository;
import kr.codeback.repository.CodeReviewRepository;
import kr.codeback.repository.NotificationRepository;
import kr.codeback.repository.ProjectReviewCommentRepository;
import kr.codeback.repository.ProjectReviewRepository;
import kr.codeback.service.interfaces.NotificationService;
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

		Notification notification = Notification.builder()
			.id(UUID.randomUUID())
			.member(validateType(preference, type))
			.entityID(preference.getId())
			.isRead(false)
			.message(generateMessage(preference,type))
			.build();

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

	@Override
	public Member validateType(Preference preference, String type){
		if(type.equals("codeReview")){
			CodeReview codeReview = codeReviewRepository.findById(preference.getEntityID()).orElse(null);
			return codeReview.getMember();
		} else if (type.equals("codeReviewComment")) {
			CodeReviewComment codeReviewComment = codeReviewCommentRepository.findById(preference.getEntityID()).orElse(null);
			return codeReviewComment.getMember();
		} else if (type.equals("projectReview")) {
			ProjectReview projectReview =  projectReviewRepository.findById(preference.getEntityID()).orElse(null);
			return projectReview.getMember();
		} else if (type.equals("projectReviewComment")) {
			ProjectReviewComment projectReviewComment = projectReviewCommentRepository.findById(preference.getEntityID()).orElse(null);
			return projectReviewComment.getMember();
		} else{
			return null;
		}
	}

	public String generateMessage(Preference preference, String type){

		if(type.equals("codeReview")){
			CodeReview codeReview = codeReviewRepository.findById(preference.getEntityID()).orElse(null);
			return codeReview.getMember().getNickname()+"님이" + codeReview.getTitle()+" 글에 좋아요를 눌렀습니다.";
		} else if (type.equals("codeReviewComment")) {
			CodeReviewComment codeReviewComment = codeReviewCommentRepository.findById(preference.getEntityID()).orElse(null);
			return codeReviewComment.getMember().getNickname() +"님이" + codeReviewComment.getCodeReview().getTitle()+"에 달린 댓글에 좋아요를 눌렀습니다.";
		} else if (type.equals("projectReview")) {
			ProjectReview projectReview =  projectReviewRepository.findById(preference.getEntityID()).orElse(null);
			return projectReview.getMember().getNickname() + projectReview.getTitle()+" 글에 좋아요를 눌렀습니다.";
		} else if (type.equals("projectReviewComment")) {
			ProjectReviewComment projectReviewComment = projectReviewCommentRepository.findById(preference.getEntityID()).orElse(null);
			return projectReviewComment.getMember().getNickname() + projectReviewComment.getProjectReview().getTitle()+"에 달린 댓글에 좋아요를 눌렀습니다.";
		} else{
			return null;
		}
	}

	@Override
	public Notification getNotificationById(UUID id) {
		return notificationRepository.findById(id).orElse(null);
	}

	@Override
	public int countByMember(Member member) {
		return Integer.parseInt(String.valueOf(notificationRepository.countByMember(member)));
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
	@Transactional
	public List<Notification> getNotifications(Member member) {
		List<Notification> notifications = notificationRepository.findAllByMember(member);
		notifications.sort(Comparator.comparing(Notification::getCreateDate).reversed());
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
