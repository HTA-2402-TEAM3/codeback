package kr.codeback.model.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CODE_LANGUAGE_CATEGORY")
@NoArgsConstructor
@Getter
public class CodeLanguageCategory {

	@Id
	private UUID id;

	@Column(name = "language_name", nullable = false)
	private String languageName;

	@Builder
	private CodeLanguageCategory(UUID id, String languageName) {
		this.id = id;
		this.languageName = languageName;
	}
}
