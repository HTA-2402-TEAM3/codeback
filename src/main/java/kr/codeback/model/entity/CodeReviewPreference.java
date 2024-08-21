package kr.codeback.model.entity;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Nullable;
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
@Table(name = "CODE_REVIEW_PREFERENCE")
@NoArgsConstructor
@Getter
public class CodeReviewPreference {

	@Id
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_email", nullable = false)
	private Member member;

	@Column(name = "entity_id", nullable = false)
	private String entityID;

	@Column(name = "is_like", columnDefinition = "TINYINT(1)")
	private boolean isLike;

	@Column(name = "create_date", updatable = false)
	@CreationTimestamp
	private Timestamp createDate;

	@Builder
	public CodeReviewPreference(UUID id, Member member, String entityID, boolean isLike) {
		this.id = id;
		this.member = member;
		this.entityID = entityID;
		this.isLike = isLike;
	}
}
