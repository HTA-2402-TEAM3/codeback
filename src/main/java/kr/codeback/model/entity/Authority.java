package kr.codeback.model.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "AUTHORITY")
@NoArgsConstructor
@Getter
@ToString
public class Authority {

	@Id
	private UUID id;

	@Column(name = "name", nullable = false)
	private String name;

	@Builder
	private Authority(UUID id, String name) {
		this.id = id;
		this.name = name;
	}
}
