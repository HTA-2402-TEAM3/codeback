package kr.codeback.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.codeback.repository.ProjectReviewCommentRepository;
import kr.codeback.service.interfaces.ProjectReviewCommentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectReviewCommentServiceImpl implements ProjectReviewCommentService {

	private final ProjectReviewCommentRepository projectReviewCommentRepository;
	@Override
	@Transactional
	public void deleteAllByProjectReviewId(UUID projectReviewId) {
		projectReviewCommentRepository.deleteAllByProjectReviewId(projectReviewId);
	}
}
