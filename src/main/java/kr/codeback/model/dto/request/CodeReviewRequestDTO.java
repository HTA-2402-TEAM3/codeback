package kr.codeback.model.dto.request;

import lombok.Getter;
import java.util.UUID;

@Getter
public class CodeReviewRequestDTO {
    private String memberEmail;
    private String title;
    private String content;
    private UUID codeLanguageCategoryId;
}
