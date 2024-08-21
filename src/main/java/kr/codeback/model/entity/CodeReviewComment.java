package kr.codeback.model.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email", nullable = false)
	private Member member;

	@Column(name = "comment", nullable = false)
	private String comment;

	@Column(name = "create_date", updatable = false)
	@CreationTimestamp
	private Timestamp createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code_review_id", nullable = false)
	private CodeReview codeReview;

	// TODO : 추후 양방향 맵핑 고려
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "base_comment_id")
	private CodeReviewComment baseComment;

	@Builder
	private CodeReviewComment(UUID id, Member member, String comment, CodeReview codeReview,
		CodeReviewComment baseComment) {
		this.id = id;
		this.member = member;
		this.comment = comment;
		this.codeReview = codeReview;
		this.baseComment = baseComment;
	}
}
