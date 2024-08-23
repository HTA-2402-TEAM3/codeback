package kr.codeback.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.codeback.model.dto.DTOSample;
import kr.codeback.model.dto.response.CodeReviewResponseDTO;
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

	@Override
	public List<CodeReviewResponseDTO> findCodeReviewAll(int pageNum, int pageSize, String sort) {
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, sort));
		Page<CodeReviewResponseDTO> dtoSamples = codeReviewRepository.findAll(pageable)
				.map((CodeReview codeReview) -> CodeReviewResponseDTO.builder()
						.id(codeReview.getId())
						.member(codeReview.getMember())
						.title(codeReview.getTitle())
						.content(codeReview.getContent())
						.createDate(codeReview.getCreateDate())
						.codeLanguageName(codeReview.getCodeLanguageCategory().getLanguageName())
						.codeReviewComments(codeReview.getComments())
						.build()
				);

		return dtoSamples.getContent();
	}

	@Override
	public CodeReview findById(UUID id) {

		Optional<CodeReview> optionalCodeReview = codeReviewRepository.findById(id);

		return optionalCodeReview.orElseThrow(
			() -> new IllegalArgumentException("No code review found with id: " + id)
		);

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
}
