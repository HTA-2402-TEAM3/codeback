package kr.codeback.model.dto.request.notification;

import java.util.UUID;

import kr.codeback.model.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationRequestDTO {

	String memberEmail;
	private String message;
	private UUID entityID;
	private boolean isRead;

	@Builder
	public NotificationRequestDTO(String memberEmail, String message, UUID entityID, boolean isRead) {
		this.memberEmail = memberEmail;
		this.message = message;
		this.entityID = entityID;
		this.isRead = isRead;
	}
}
