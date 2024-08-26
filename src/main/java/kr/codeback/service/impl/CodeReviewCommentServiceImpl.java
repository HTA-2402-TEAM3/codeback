package kr.codeback.service.impl;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.repository.CodeReviewCommentRepository;
import kr.codeback.service.interfaces.CodeReviewCommentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeReviewCommentServiceImpl implements CodeReviewCommentService {

	private final CodeReviewCommentRepository codeReviewCommentRepository;

	@Override
	@Transactional
	public void deleteAllByEmail(String deleteEmail) {
		codeReviewCommentRepository.deleteAllByEmail(deleteEmail);
	}

}
