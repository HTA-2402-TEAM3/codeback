package kr.codeback.model.entity;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import kr.codeback.model.dto.request.review.ProjectReviewModifyRequestDTO;
import kr.codeback.model.dto.request.review.ProjectReviewRequestDTO;
import kr.codeback.model.dto.response.review.ProjectReviewModifyResponseDTO;
import kr.codeback.model.dto.response.review.set.ProjectReviewImageDTO;
import kr.codeback.model.dto.response.review.set.ProjectReviewTagDTO;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.templateresolver.FileTemplateResolver;

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

	@Column(name = "content", nullable = false, length = 65535)
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

	public ProjectReview addSet(Set<ProjectReviewImage> imageSet, Set<ProjectReviewTag> tagSet) {
		return ProjectReview.builder()
				.projectReviewImages(imageSet)
				.projectReviewTags(tagSet)
				.build();
	}

	public ProjectReviewModifyResponseDTO toModifyDTO() {
		Set<ProjectReviewTagDTO> tagDTO = projectReviewTags.stream().map(tag->ProjectReviewTagDTO.builder()
				.id(tag.getId())
				.tag(tag.getTag())
				.build()).collect(Collectors.toSet());
		Set<ProjectReviewImageDTO> imageDTO = projectReviewImages.stream().map(image->ProjectReviewImageDTO.builder()
				.url(image.getUrl())
				.fileName(image.getFileName())
				.id(image.getId())
				.build()).collect(Collectors.toSet());


		return ProjectReviewModifyResponseDTO.builder()
				.tags(tagDTO)
				.images(imageDTO)
				.title(title)
				.content(content)
				.githubUrl(githubURL)
				.build();
	}

    public void updateProjectReview(ProjectReviewModifyRequestDTO projectDTO) {
		title = projectDTO.getTitle();
		content = projectDTO.getContent();
		githubURL = projectDTO.getGithubUrl();
	}

	public void deleteProjectReviewImages(List<ProjectReviewImage> deleteImages) {
		deleteImages.forEach(image -> {
			this.projectReviewImages.remove(image);
			image.setProjectReview(null);
		});

	}

	public void addProjectReviewImages(List<ProjectReviewImage> addImages) {
		addImages.forEach(image -> {
			this.projectReviewImages.add(image);
			image.setProjectReview(this);
		});
	}

	public void deleteProjectReviewTags(List<ProjectReviewTag> tagsDelete) {
		tagsDelete.forEach(tag -> {
			this.projectReviewTags.remove(tag);
			tag.setProjectReview(null);
		});
	}

	public void addProjectReviewTags(List<ProjectReviewTag> tagsAdd) {
		tagsAdd.forEach(tag -> {
			this.projectReviewTags.add(tag);
			tag.setProjectReview(this);
		});
	}
}
