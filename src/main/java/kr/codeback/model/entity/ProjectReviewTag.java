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
@Table(name = "PROJECT_REVIEW_TAG")
@NoArgsConstructor
@Getter
public class ProjectReviewTag {

	@Id
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_review_id")
	private ProjectReview projectReview;

	@Column(name = "tag")
	private String tag;

	@Builder
	public ProjectReviewTag(UUID id, ProjectReview projectReview, String tag) {
		this.id = id;
		this.projectReview = projectReview;
		this.tag = tag;
	}
}
