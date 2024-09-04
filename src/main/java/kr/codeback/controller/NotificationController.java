package kr.codeback.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import kr.codeback.model.entity.Notification;

@Controller
public class NotificationController {
	private final List<Notification> notifications = new ArrayList<>();

	@GetMapping("/notice")
	public String index(Model model) {
		model.addAttribute("notifications", notifications);
		return "view/notification/notification"; // Thymeleaf 템플릿 이름
	}

	@PostMapping("/notifications")
	public void addNotification(@RequestBody Notification notification) {
		notifications.add(notification);
	}

	@GetMapping("/notifications")
	public List<Notification> getNotifications() {
		return notifications;
	}
}