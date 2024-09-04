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
@Table(name = "CODE_REVIEW_PREFERENCE")
@NoArgsConstructor
@Getter
public class Preference {

	@Id
	private UUID id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_member_id", nullable = false)
	private Member member;

//	게시글,댓글아이디가될수있다
	@Column(name = "entity_id", nullable = false)
	private UUID entityID;

	@Column(name = "is_like", columnDefinition = "TINYINT(1)")
	private boolean isLike;

	@Column(name = "create_date", updatable = false)
	@CreationTimestamp
	private Timestamp createDate;

	@Builder
	public Preference(UUID id, Member member, UUID entityID, boolean isLike) {
		this.id = id;
		this.member = member;
		this.entityID = entityID;
		this.isLike = isLike;
	}
}
