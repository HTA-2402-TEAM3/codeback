package kr.codeback.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.codeback.common.MessageResponseDTO;
import kr.codeback.model.entity.Notification;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationRestController {

	private final NotificationService notificationService;
	private final MemberService memberService;

	@GetMapping()
	public ResponseEntity<List<Notification>> getNotifications() {
		return ResponseEntity.ok().body(notificationService.getNotifications(memberService.extractMember()));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> markAsRead(@PathVariable UUID id) {
		notificationService.markAsRead(notificationService.getNotificationById(id));
		return ResponseEntity.ok(new MessageResponseDTO("읽음 처리되었습니다."));
	}

	@PatchMapping()
	public ResponseEntity<?> markAll() {
		notificationService.markAll(memberService.extractMember());
		return ResponseEntity.ok(new MessageResponseDTO("읽음 처리되었습니다."));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable UUID id){
		notificationService.delete(id);
		return ResponseEntity.ok(new MessageResponseDTO("알림이 삭제되었습니다."));
	}

	@DeleteMapping()
	public ResponseEntity<?> deleteAll(){
		notificationService.deleteByMember(memberService.extractMember());
		return ResponseEntity.ok(new MessageResponseDTO("알림이 삭제되었습니다."));
	}

	@GetMapping("/count")
	public ResponseEntity<?> count(){
		int count = notificationService.countNotifications(notificationService.getNotifications(memberService.extractMember()));
		return ResponseEntity.ok(count);
	}
}
