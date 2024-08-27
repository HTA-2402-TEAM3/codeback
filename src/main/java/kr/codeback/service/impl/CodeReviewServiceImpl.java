package kr.codeback.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.codeback.model.dto.request.CodeReviewRequestDTO;
import kr.codeback.model.dto.response.review.CodeReviewListResponseDTO;
import kr.codeback.model.dto.response.review.CodeReviewResponseDTO;
import kr.codeback.model.entity.CodeLanguageCategory;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.CodeLanguageCategoryRepository;
import kr.codeback.repository.MemberRepository;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.codeback.model.entity.CodeReview;
import kr.codeback.repository.CodeReviewRepository;
import kr.codeback.service.interfaces.CodeReviewService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeReviewServiceImpl implements CodeReviewService {

    private final CodeReviewRepository codeReviewRepository;
    private final CodeReviewPreferenceService codeReviewPreferenceService;
    private final MemberRepository memberRepository;
    private final CodeLanguageCategoryRepository codeLanguageCategoryRepository;

    @Override
    public Page<CodeReviewListResponseDTO> findCodeReviewAll(int pageNum, int pageSize, String sort) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, sort));
        Page<CodeReviewListResponseDTO> codeReviewResponseDTOS = codeReviewRepository.findAll(pageable)
                .map((CodeReview codeReview) -> CodeReviewListResponseDTO.builder()
                        .id(codeReview.getId())
                        .member(codeReview.getMember().getNickname())
                        .title(codeReview.getTitle())
                        .content(codeReview.getContent())
                        .createDate(codeReview.getCreateDate())
                        .codeLanguageName(codeReview.getCodeLanguageCategory().getLanguageName())
                        .codeReviewComments(codeReview.getComments().size())
                        .preferenceCnt(codeReviewPreferenceService.findById(codeReview.getId()).size())
                        .build()
                );

        return codeReviewResponseDTOS;
    }

    @Override
    public Page<CodeReviewListResponseDTO> findCodeReviewByLanguage(UUID language, int pageNum, int pageSize, String sort) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, sort));
        Page<CodeReviewListResponseDTO> codeReviewResponseDTOS =
                codeReviewRepository.findByCodeLanguageCategory_Id(language, pageable).map(
                        (CodeReview codeReview) -> CodeReviewListResponseDTO.builder()
                                .id(codeReview.getId())
                                .member(codeReview.getMember().getNickname())
                                .title(codeReview.getTitle())
                                .content(codeReview.getContent())
                                .createDate(codeReview.getCreateDate())
                                .codeLanguageName(codeReview.getCodeLanguageCategory().getLanguageName())
                                .preferenceCnt(codeReviewPreferenceService.findById(codeReview.getId()).size())
                                .codeReviewComments(codeReview.getComments().size())
                                .build()
                );
        return codeReviewResponseDTOS;
    }


    @Override
    public CodeReview findById(UUID id) {
        Optional<CodeReview> optionalCodeReview = codeReviewRepository.findById(id);
        return optionalCodeReview.orElseThrow(() -> new IllegalArgumentException("No CodeReview : " + id));
    }

    @Override
    public List<CodeReview> findCodeReviewByAuthor(String author) {
        return null;
    }

    @Override
    public List<CodeReview> findCodeReviewByTitle(String title) {
        return null;
    }

    @Override
    public Boolean deleteCodeReviewById(String id) {
        return null;
    }

    @Override
    public CodeReview saveCodeReview(CodeReviewRequestDTO codeReviewRequestDTO) {
        Member member = memberRepository.findByEmail(codeReviewRequestDTO.getMemberEmail())
                .orElseThrow(()->new IllegalArgumentException("cannot find member by email: "+codeReviewRequestDTO.getMemberEmail()));
//        Member entity
        CodeLanguageCategory codeLanguageCategory = codeLanguageCategoryRepository
                .findById(codeReviewRequestDTO.getCodeLanguageCategoryId())
                .orElseThrow(()-> new IllegalArgumentException("cannot find language by id: "+codeReviewRequestDTO.getCodeLanguageCategoryId()));
//        CodeLanguageCategory entity

        CodeReview codeReview = CodeReview.builder()
                .id(UUID.randomUUID())
                .title(codeReviewRequestDTO.getTitle())
                .content(codeReviewRequestDTO.getContent())
                .member(member)
                .codeLanguageCategory(codeLanguageCategory)
                .build();
//        reqDTO -> CodeReview entity

        return codeReviewRepository.save(codeReview);
    }
}