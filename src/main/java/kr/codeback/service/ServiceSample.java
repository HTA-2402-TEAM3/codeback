package kr.codeback.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.codeback.model.dto.DTOSample;
import kr.codeback.model.entity.CodeReview;
import kr.codeback.repository.CodeReviewRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceSample {

	private final CodeReviewRepository codeReviewRepository;

	public List<DTOSample> findCodeReviewAll(int pageNum, int pageSize, String sort) {

		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, sort));
		Page<DTOSample> dtoSamples = codeReviewRepository.findAll(pageable)
			.map((CodeReview codeReview) -> DTOSample.builder()
				.id(codeReview.getId())
				.nickname(codeReview.getMember().getNickname())
				.title(codeReview.getTitle())
				.content(codeReview.getContent())
				.createDate(codeReview.getCreateDate())
				.codeLanguageName(codeReview.getCodeLanguageCategory().getLanguageName())
				.build()
			);

		return dtoSamples.getContent();
	}

}
