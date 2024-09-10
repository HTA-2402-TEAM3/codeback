package kr.codeback.model.entity;

import java.util.Objects;
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
@Table(name = "PROJECT_REVIEW_IMAGE")
@NoArgsConstructor
@Getter
public class ProjectReviewImage {

    @Id
    private UUID id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_review_id")
    private ProjectReview projectReview;

    @Builder
    public ProjectReviewImage(UUID id, String fileName, String url, ProjectReview projectReview) {
        this.id = id;
        this.fileName = fileName;
        this.url = url;
        this.projectReview = projectReview;
    }

    public void dissociateReview() {
        this.projectReview = null;
    }

    public void setProjectReview(ProjectReview projectReview) {
        this.projectReview = projectReview;
    }
}
