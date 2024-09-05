package kr.codeback.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.codeback.common.MessageResponseDTO;
import kr.codeback.model.entity.Notification;
import kr.codeback.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationRestController {

	private final NotificationService notificationService;

	@GetMapping("/")
	public ResponseEntity<List<Notification>> getNotifications() {
		return ResponseEntity.ok(notificationService.getAllNotifications());
	}

	// 특정 회원의 알림 조회 (HTTP GET 요청)
	@GetMapping("/{id}")
	public ResponseEntity<?> markAsRead(@PathVariable UUID id) {
		notificationService.markAsRead(notificationService.getNotificationById(id));
		return ResponseEntity.ok(new MessageResponseDTO("읽음 처리되었습니다."));
	}

	@GetMapping("/mark")
	public ResponseEntity<?> markAll() {
		notificationService.markAll();
		return ResponseEntity.ok(new MessageResponseDTO("읽음 처리되었습니다."));
	}

}
