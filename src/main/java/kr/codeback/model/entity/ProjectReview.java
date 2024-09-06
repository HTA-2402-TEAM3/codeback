package kr.codeback.model.entity;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROJECT_REVIEW")
@NoArgsConstructor
@Getter
public class ProjectReview {
	@Id
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "github_url", nullable = false)
	private String githubURL;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "create_date", updatable = false)
	@CreationTimestamp
	private Timestamp createDate;

	@OneToMany(mappedBy = "projectReview", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
		orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<ProjectReviewImage> projectReviewImages = new LinkedHashSet<>();

	@OneToMany(mappedBy = "projectReview", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
		orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<ProjectReviewTag> projectReviewTags = new LinkedHashSet<>();

	@OneToMany(mappedBy = "projectReview", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("createDate DESC")
	private List<ProjectReviewComment> comments;


	//TODO : 리뷰 답글 양방향 맵핑 고려

	@Builder
	public ProjectReview(UUID id, Member member, String title, String githubURL, String content,
		Set<ProjectReviewImage> projectReviewImages, Set<ProjectReviewTag> projectReviewTags
	,List<ProjectReviewComment> comments) {
		this.id = id;
		this.member = member;
		this.title = title;
		this.githubURL = githubURL;
		this.content = content;
		this.projectReviewImages = projectReviewImages;
		this.projectReviewTags = projectReviewTags;
		this.comments = comments;
	}
}
