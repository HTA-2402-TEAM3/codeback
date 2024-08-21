package kr.codeback.model.entity;

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

@Entity
@Table(name = "PROJECT_REVIEW_COMMENT")
@NoArgsConstructor
@Getter
public class ProjectReviewComment {

	@Id
	private UUID id;

	@Column
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_review_id")
	private ProjectReview projectReview;

	@Builder
	public ProjectReviewComment(UUID id, String content, ProjectReview projectReview) {
		this.id = id;
		this.content = content;
		this.projectReview = projectReview;
	}
}
