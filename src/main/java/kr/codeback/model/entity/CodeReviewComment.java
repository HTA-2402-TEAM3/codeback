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
@Table(name = "CODE_REVIEW_COMMENT")
@NoArgsConstructor
@Getter
public class CodeReviewComment {

	@Id
	private UUID id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "email", nullable = false)
	private Member member;

	@Column(name = "comment", nullable = false, length = 2000)
	private String comment;

	@Column(name = "create_date", updatable = false)
	@CreationTimestamp
	private Timestamp createDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "code_review_id", nullable = false)
	private CodeReview codeReview;

	@Builder
	private CodeReviewComment(UUID id, Member member, String comment, CodeReview codeReview) {
		this.id = id;
		this.member = member;
		this.comment = comment;
		this.codeReview = codeReview;
	}
}
