package kr.codeback.model.dto.request.review;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
public class ProjectReviewModifyRequestDTO {
    private String memberEmail;
    private List<MultipartFile> imageFiles;
    private String githubUrl;
    private String title;
    private String content;
    private List<String> tags;
    private List<String> fileNames;

    public ProjectReviewModifyRequestDTO(String memberEmail, List<MultipartFile> imageFiles, String githubUrl, String title, String content, List<String> fileNames, List<String> tags) {
        this.memberEmail = memberEmail;
        this.imageFiles = imageFiles;
        this.githubUrl = githubUrl;
        this.title = title;
        this.content = content;
        this.tags =tags;
        this.fileNames = fileNames;
    }
}
