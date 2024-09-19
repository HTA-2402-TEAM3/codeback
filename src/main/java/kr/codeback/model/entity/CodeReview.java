package kr.codeback.model.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import kr.codeback.model.dto.request.review.CodeReviewRequestDTO;
import kr.codeback.model.dto.response.review.CodeReviewModifyResponseDTO;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CODE_REVIEW")
@NoArgsConstructor
@Getter
public class CodeReview {

	@Id
	private UUID id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false, length = 3000)
	private String content;

	@Column(name = "create_date", updatable = false)
	@CreationTimestamp
	private Timestamp createDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "language_id", nullable = false)
	private CodeLanguageCategory codeLanguageCategory;

	@OneToMany(mappedBy = "codeReview", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("createDate DESC")
	private List<CodeReviewComment> comments;

	@Builder
	private CodeReview(UUID id, Member member, String title, String content,
		CodeLanguageCategory codeLanguageCategory, List<CodeReviewComment> comments) {
		this.id = id;
		this.member = member;
		this.title = title;
		this.content = content;
		this.codeLanguageCategory = codeLanguageCategory;
		this.comments = comments;
	}

	public void updateCodeReview(CodeReviewRequestDTO codeReviewDTO, CodeLanguageCategory clCategory) {
		title = codeReviewDTO.getTitle();
		content = codeReviewDTO.getContent();
		codeLanguageCategory = clCategory;
	}

	public CodeReviewModifyResponseDTO toModifyDTO() {
		return CodeReviewModifyResponseDTO.builder()
				.codeLanguageCategory(codeLanguageCategory)
				.title(title)
				.content(content)
				.build();
	}
}
