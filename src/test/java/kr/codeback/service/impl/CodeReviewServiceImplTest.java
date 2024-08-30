package kr.codeback.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import kr.codeback.model.dto.request.review.CodeReviewRequestDTO;
import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.CodeLanguageCategory;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.CodeLanguageCategoryRepository;
import kr.codeback.repository.CodeReviewRepository;
import kr.codeback.repository.MemberRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CodeReviewServiceImplTest {

    @InjectMocks
    private CodeReviewServiceImpl codeReviewService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CodeLanguageCategoryRepository codeLanguageCategoryRepository;

    @Mock
    private CodeReviewRepository codeReviewRepository;

    private Member member;
    private CodeLanguageCategory codeLanguageCategory;

    @BeforeEach
    void setUp() {
        Authority authority = Authority.builder()
                .id(UUID.fromString("5e2ef0c1-6502-11ef-8e2a-0242ac140002"))
                .name("ROLE_USER")
                .build();

        member = Member.builder()
                .email("test@test.com")
                .authority(authority)
                .nickname("test")
                .id(UUID.randomUUID())
                .build();

        codeLanguageCategory = CodeLanguageCategory.builder()
                .id(UUID.fromString("5e2f57ad-6502-11ef-8e2a-0242ac140002"))
                .languageName("Python")
                .build();
    }

    @Test
    @DisplayName("CodeReview save Test")
    void saveCodeReview() {

        // given
        CodeReviewRequestDTO requestDTO = CodeReviewRequestDTO.builder()
                .title("Test Title")
                .content("Test Content")
                .id(UUID.randomUUID())
                .codeLanguageCategoryId(UUID.fromString("5e2f57ad-6502-11ef-8e2a-0242ac140002"))
                .memberEmail("test@example.com")
                .build();


        // Mocking
        when(memberRepository.findByEmail(requestDTO.getMemberEmail())).thenReturn(Optional.of(member));
        when(codeLanguageCategoryRepository.findById(requestDTO.getCodeLanguageCategoryId())).thenReturn(Optional.of(codeLanguageCategory));

        // when
        codeReviewService.saveCodeReview(requestDTO);

        // then
        verify(codeReviewRepository, times(1)).save(any(CodeReview.class));
    }

}
