package kr.codeback.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.codeback.common.MessageResponseDTO;
import kr.codeback.model.constant.SuccessMessage;
import kr.codeback.model.dto.request.notification.NotificationRequestDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Notification;
import kr.codeback.repository.NotificationRepository;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationRestController {

	private final NotificationService notificationService;
	private final MemberService memberService;

	@GetMapping("/")
	public ResponseEntity<List<Notification>> getNotifications() {
		return ResponseEntity.ok(notificationService.getAllNotifications());
	}
	// 테스트를 위한 POST 메서드 추가 (알림 추가)

	@PostMapping("/")
	public void createNotification(@RequestBody NotificationRequestDTO notificationRequestDTO) {

		Notification notification = Notification.builder()
			.id(UUID.randomUUID())
			.entityID(notificationRequestDTO.getEntityID())
			.message(notificationRequestDTO.getMessage())
			.member(memberService.findByEmail(notificationRequestDTO.getMemberEmail()))
			.isRead(false)
			.build();

		notificationService.addNotification(notification);

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> markAsRead(@PathVariable UUID id) {
		notificationService.markAsRead(notificationService.getNotificationById(id));
		return ResponseEntity.ok(new MessageResponseDTO("읽음 처리되었습니다."));
	}    // 특정 회원의 알림 조회 (HTTP GET 요청)

	@GetMapping("/mark")
	public ResponseEntity<?> markAll(){
		notificationService.markAll();
		return ResponseEntity.ok(new MessageResponseDTO("읽음 처리되었습니다."));
	}

}
