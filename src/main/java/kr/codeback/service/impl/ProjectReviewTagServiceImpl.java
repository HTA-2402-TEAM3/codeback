package kr.codeback.service.impl;

import jakarta.transaction.Transactional;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewTag;
import kr.codeback.repository.ProjectReviewTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import kr.codeback.service.interfaces.ProjectReviewTagService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectReviewTagServiceImpl implements ProjectReviewTagService {
    private final ProjectReviewTagRepository projectReviewTagRepository;
    @Override
    @Transactional
    public Set<ProjectReviewTag> save(List<String> tags, ProjectReview reviewObj) {
        Set<ProjectReviewTag> tagSet = new LinkedHashSet<>();
        for (String tagName : tags) {
            ProjectReviewTag tag = ProjectReviewTag.builder()
                    .id(UUID.randomUUID())
                    .projectReview(reviewObj)
                    .tag(tagName)
                    .build();

            tagSet.add(tag);
        }
        projectReviewTagRepository.saveAll(tagSet);

        return tagSet;
    }

    //프로젝트 리뷰 삭제시 달려있는 모든 태그 삭제
    @Override
    public void deleteAllByProjectReviewId(UUID projectReviewID) {
        projectReviewTagRepository.deleteAllByProjectReviewId(projectReviewID);
    }

    @Override
    public Set<ProjectReviewTag> updateTags(List<String> tags, ProjectReview projectReview) {
        Set<ProjectReviewTag> tagSet = null;
        return tagSet;
    }

}
