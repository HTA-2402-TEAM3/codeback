package kr.codeback.model.dto.request.notification;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationRequestDTO {

	LinkedHashMap<String, UUID> type;

	@Builder
	public NotificationRequestDTO(LinkedHashMap<String, UUID> type) {
		this.type = type;
	}

}
