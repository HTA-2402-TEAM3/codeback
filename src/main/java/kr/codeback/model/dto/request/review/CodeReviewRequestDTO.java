package kr.codeback.model.dto.request.review;

import lombok.Builder;
import lombok.Getter;
import org.aspectj.apache.bcel.classfile.Code;

import java.util.UUID;

@Getter
public class CodeReviewRequestDTO {
    private final UUID id;
    private final String memberEmail;
    private final String title;
    private final String content;
    private final UUID codeLanguageCategoryId;
    @Builder
    private CodeReviewRequestDTO(UUID id, String memberEmail, String title, String content,
                                  UUID codeLanguageCategoryId) {
        this.codeLanguageCategoryId = codeLanguageCategoryId;
        this.id = id;
        this.memberEmail = memberEmail;
        this.title = title;
        this.content = content;
    }
}
