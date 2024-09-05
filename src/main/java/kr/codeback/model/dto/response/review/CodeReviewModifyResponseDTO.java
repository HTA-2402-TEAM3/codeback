package kr.codeback.model.dto.response.review;

import kr.codeback.model.entity.CodeLanguageCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CodeReviewModifyResponseDTO {
    private final String title;
    private final String content;
    private final CodeLanguageCategory codeLanguageCategory;

    @Builder
    public CodeReviewModifyResponseDTO(String title, String content, CodeLanguageCategory codeLanguageCategory) {
        this.title = title;
        this.content = content;
        this.codeLanguageCategory = codeLanguageCategory;
    }
}
