package kr.codeback.model.dto.request.review;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class ProjectReviewRequestDTO {
    private String memberEmail;
    private List<MultipartFile> imageFiles;
    private String githubUrl;
    private String title;
    private String content;
    private List<String> tags;

    @Builder
    public ProjectReviewRequestDTO(String memberEmail, List<MultipartFile> imageFiles, String githubUrl,
                                   String title, String content, List<String> tags) {
        this.tags = tags;
        this.content = content;
        this.memberEmail = memberEmail;
        this.imageFiles = imageFiles;
        this.githubUrl = githubUrl;
        this.title = title;
    }
}

