package kr.codeback.model.dto;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DTOSample {

	private final UUID id;
	private final String nickname;
	private final String title;
	private final String content;
	private final Timestamp createDate;
	private final String codeLanguageName;

	@Builder
	public DTOSample(UUID id, String nickname, String title, String content, Timestamp createDate,
		String codeLanguageName) {
		this.id = id;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.codeLanguageName = codeLanguageName;
	}
}
