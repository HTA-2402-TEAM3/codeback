package kr.codeback.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.model.dto.request.CodeReviewRequestDTO;
import kr.codeback.model.dto.response.summary.CodeReviewSummaryByLanguageResponseDTO;
import kr.codeback.model.dto.response.summary.CodeReviewSummaryByMonthResponseDTO;
import kr.codeback.model.dto.response.review.CodeReviewListResponseDTO;
import kr.codeback.model.entity.CodeLanguageCategory;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.CodeLanguageCategoryRepository;
import kr.codeback.repository.CodeReviewRepository;
import kr.codeback.repository.MemberRepository;
import kr.codeback.service.interfaces.CodeReviewCommentService;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import kr.codeback.service.interfaces.CodeReviewService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeReviewServiceImpl implements CodeReviewService {

	private final CodeReviewRepository codeReviewRepository;

	private final CodeReviewPreferenceService codeReviewPreferenceService;
	private final MemberRepository memberRepository;
	private final CodeLanguageCategoryRepository codeLanguageCategoryRepository;

	private final CodeReviewCommentService codeReviewCommentService;

	@Override
	public Page<CodeReviewListResponseDTO> findAllWithPage(int pageNum, int pageSize, String sort) {

		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, sort));

		return codeReviewRepository.findAll(pageable)
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
	}

	@Override
	public Page<CodeReviewListResponseDTO> findCodeReviewByLanguage(UUID language, int pageNum, int pageSize,
		String sort) {
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, sort));

		return codeReviewRepository.findByCodeLanguageCategoryId(language, pageable).map(
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
	@Transactional
	public void deleteByMember(Member member) {

		List<CodeReview> deleteCodeReviews = findByMember(member);

		deleteCodeReviews.forEach(codeReviewCommentService::deleteByCodeReview);
		deleteCodeReviews.forEach(codeReview -> codeReviewPreferenceService.deleteByEntityID(codeReview.getId()));

		codeReviewRepository.deleteAll(deleteCodeReviews);
	}

	@Override
	public List<CodeReview> findByMember(Member member) {
		return codeReviewRepository.findByMemberEmail(member.getEmail());
	}

	@Override
	@Transactional
	public Boolean deleteCodeReviewById(UUID id) {
		CodeReview codeReview = codeReviewRepository.findById(id).orElseThrow(() ->
			new IllegalArgumentException("no CodeReview : " + id));
		codeReviewRepository.delete(codeReview);

		return true;
	}

	@Override
	public CodeReview saveCodeReview(CodeReviewRequestDTO codeReviewRequestDTO) {
		Member member = memberRepository.findByEmail(codeReviewRequestDTO.getMemberEmail())
			.orElseThrow(() -> new IllegalArgumentException(
				"cannot find member by email: " + codeReviewRequestDTO.getMemberEmail()));
		//        Member entity
		CodeLanguageCategory codeLanguageCategory = codeLanguageCategoryRepository
			.findById(codeReviewRequestDTO.getCodeLanguageCategoryId())
			.orElseThrow(() -> new IllegalArgumentException(
				"cannot find language by id: " + codeReviewRequestDTO.getCodeLanguageCategoryId()));
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

	@Override
	public List<CodeReviewSummaryByLanguageResponseDTO> calculateSummaryByLanguage() {
		return codeReviewRepository.calculateSummaryByLanguage();
	}

	@Override
	public List<CodeReviewSummaryByMonthResponseDTO> calculateSummaryByMonth(String inputDate) {


		Date searchDate = null;
		if (inputDate == null || inputDate.isEmpty()) {
			searchDate = Date.valueOf(LocalDate.now());
		} else {
			searchDate = Date.valueOf(LocalDate.parse(inputDate));
		}

		List<Object[]> results = codeReviewRepository.calculateSummaryByMonth(searchDate);

		return results.stream()
			.map(row -> new CodeReviewSummaryByMonthResponseDTO(
				Integer.parseInt(row[0].toString()),
				((Number)row[1]).longValue()
			))
			.toList();
	}

}

