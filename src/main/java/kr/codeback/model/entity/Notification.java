package kr.codeback.model.entity;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NOTIFICATION")
@NoArgsConstructor
@Getter
public class Notification {

	@Id
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_email", nullable = false)
	private Member member;

	@Column(name = "entity_id", nullable = false)
	private UUID entityID;

	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "is_read", columnDefinition = "TINYINT(1)")
	private boolean isRead;

	@Column(name = "create_date", updatable = false)
	@CreationTimestamp
	private Timestamp createDate;

	@Builder
	public Notification(UUID id, Member member, UUID entityID, String message, boolean isRead) {
		this.id = id;
		this.member = member;
		this.entityID = entityID;
		this.message = message;
		this.isRead = isRead;
	}
}
