package kr.codeback.model.entity;

import java.sql.Timestamp;
import java.util.UUID;

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
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "PROJECT_REVIEW_COMMENT")
@NoArgsConstructor
@Getter
public class ProjectReviewComment {

	@Id
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Column
	private String content;

	@Column(name = "create_date" ,updatable = false)
	@CreationTimestamp
	private Timestamp createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_review_id")
	private ProjectReview projectReview;

	@Builder
	public ProjectReviewComment(UUID id, Member member, String content, ProjectReview projectReview) {
		this.id = id;
		this.member = member;
		this.content = content;
		this.projectReview = projectReview;
	}
}
