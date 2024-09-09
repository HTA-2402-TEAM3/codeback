package kr.codeback.service.interfaces;

import java.util.UUID;

public interface ProjectReviewCommentService {

	void deleteAllByProjectReviewId(UUID projectReviewId);
}
