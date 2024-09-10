package kr.codeback.service.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.weaver.ast.Not;

import kr.codeback.model.dto.request.review.CodeReviewCommentRequestDTO;
import kr.codeback.model.entity.CodeReviewComment;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Notification;
import kr.codeback.model.entity.Preference;

public interface NotificationService {

	void save(CodeReviewComment codeReviewComment);
	void save(Preference preference,String type);

	// void save(CodeReviewCommentRequestDTO codeReviewCommentRequestDTO){}

	// ID로 알림 조회
	Notification getNotificationById(UUID id);

	void deleteByMember(Member member);

	void update(Notification notification);

	List<Notification> getNotifications(Member member);

	List<Notification> findByEntityID(UUID entityID);

    void deleteAll(Member member);

	void deleteByEntityId(UUID entityId);

	void markAsRead(Notification notification);

	void delete(UUID id);
	void markAll(Member member);
}
