package kr.codeback.model.dto.response.review;

import kr.codeback.model.entity.CodeReviewComment;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
public class CodeReviewListResponseDTO {
    private final UUID id;
    private final String member;
    private final String title;
    private final String content;
    private final Timestamp createDate;
    private final String codeLanguageName;
    private final Integer codeReviewComments;
    private final Integer preferenceCnt;

    @Builder
    private CodeReviewListResponseDTO(UUID id, String member, String title, String content, Timestamp createDate,
                                  String codeLanguageName, Integer codeReviewComments, Integer preferenceCnt) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.codeLanguageName = codeLanguageName;
        this.codeReviewComments = codeReviewComments;
        this.preferenceCnt = preferenceCnt;
    }
}
