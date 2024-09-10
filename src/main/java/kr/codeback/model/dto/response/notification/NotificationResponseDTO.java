package kr.codeback.model.dto.response.notification;

import java.sql.Timestamp;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationResponseDTO {

	String receiverEmail;
	String message;
	Boolean isRead;
	Timestamp createDate;

	@Builder
	public NotificationResponseDTO(String receiverEmail, String message, Boolean isRead) {

		this.receiverEmail = receiverEmail;
		this.message = message;
		this.isRead = isRead;
	}
}
