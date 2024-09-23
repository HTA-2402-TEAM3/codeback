package kr.codeback.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import kr.codeback.model.dto.request.review.CodeReviewRequestDTO;
import kr.codeback.model.entity.*;
import kr.codeback.repository.*;


import kr.codeback.service.interfaces.NotificationService;
import kr.codeback.service.interfaces.PreferenceService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CodeReviewServiceImplTest {

    //   service Mocks
    @InjectMocks
    private CodeReviewServiceImpl codeReviewService;
    @Mock
    private PreferenceService preferenceService;
    @Mock
    private NotificationService notificationService;

    //    repository Mocks
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private CodeLanguageCategoryRepository codeLanguageCategoryRepository;
    @Mock
    private CodeReviewRepository codeReviewRepository;


    private Member member;
    private CodeLanguageCategory codeLanguageCategory;
    private CodeReview codeReview;

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

        codeReview = CodeReview.builder()
                .member(member)
                .codeLanguageCategory(codeLanguageCategory)
                .title("Title")
                .content("Content")
                .id(UUID.randomUUID())
                .build();

    }

    @Test
    @DisplayName("CodeReview save Test : codeReview save 메서드 호출 되었는지 테스트")
    void saveCodeReview() {

        // given
        CodeReviewRequestDTO requestDTO = CodeReviewRequestDTO.builder()
                .title("Test Title")
                .content("Test Content")
                .id(UUID.randomUUID())
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

    @Test
    @DisplayName("CodeReview delete Test : preference, notification, codeReview 메서드 호출되었는지 테스트")
    void codeReviewDeleteTest() {
        // given
        List<Preference> preferences = List.of(
                Preference.builder()
                        .entityID(codeReview.getId())
                        .id(UUID.randomUUID())
                        .member(member)
                        .isLike(true)
                        .build()
        );

        // Mocking
        when(codeReviewRepository.findById(any(UUID.class))).thenReturn(Optional.of(codeReview));
        when(preferenceService.findByEntityID(codeReview.getId())).thenReturn(preferences);

        // when
        codeReviewService.deleteCodeReviewById(codeReview.getId(), member.getEmail());

        // then
        verify(preferenceService, times(1)).deleteAll(preferences);
        verify(notificationService, times(1)).deleteByEntityId(codeReview.getId());
        verify(codeReviewRepository, times(1)).delete(codeReview);
    }

    @Test
    @DisplayName("CodeReview Update Test")
    void codeReviewUpdateTest() {
        // given
        CodeReviewRequestDTO requestDTO = CodeReviewRequestDTO.builder()
                .title("Test Title")
                .content("Test Content")
                .id(codeReview.getId())
                .codeLanguageCategoryId(UUID.fromString("5e2f57ad-6502-11ef-8e2a-0242ac140002"))
                .memberEmail(member.getEmail())
                .build();

        CodeLanguageCategory languageCategory = CodeLanguageCategory.builder()
                .id(UUID.fromString("b29008d7-76ef-11ef-88b3-0242ac140002"))
                .languageName("Java")
                .build();

        // Mocking
        when(codeReviewRepository.findById(any(UUID.class))).thenReturn(Optional.of(codeReview));
        when(codeLanguageCategoryRepository.findById(requestDTO.getCodeLanguageCategoryId())).thenReturn(Optional.of(languageCategory));


        // when
        codeReviewService.updateCodeReview(requestDTO);

        // then
        verify(codeLanguageCategoryRepository, times(1)).findById(requestDTO.getCodeLanguageCategoryId());
        verify(codeReviewRepository, times(1)).save(codeReview);
    }
}