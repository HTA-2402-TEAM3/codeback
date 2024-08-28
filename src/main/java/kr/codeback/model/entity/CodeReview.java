package kr.codeback.model.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "CODE_REVIEW")
@NoArgsConstructor
@Getter
public class CodeReview {

	@Id
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email", nullable = false)
	private Member member;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false, length = 3000)
	private String content;

	@Column(name = "create_date", updatable = false)
	@CreationTimestamp
	private Timestamp createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id", nullable = false)
	private CodeLanguageCategory codeLanguageCategory;

	@OneToMany(mappedBy = "codeReview")
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
}
