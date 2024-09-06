package kr.codeback.service.interfaces;

import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewTag;

import java.util.List;
import java.util.Set;

public interface ProjectReviewTagService {
    Set<ProjectReviewTag> save(List<String> tags, ProjectReview reviewObj);
}
