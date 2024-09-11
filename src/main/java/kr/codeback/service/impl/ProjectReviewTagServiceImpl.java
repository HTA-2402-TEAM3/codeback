package kr.codeback.service.impl;

import jakarta.transaction.Transactional;
import kr.codeback.model.entity.ProjectReview;
import kr.codeback.model.entity.ProjectReviewTag;
import kr.codeback.repository.ProjectReviewTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import kr.codeback.service.interfaces.ProjectReviewTagService;

import java.util.*;

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
    public ProjectReview updateTags(ProjectReview projectReview, List<String> tags) {
        Set<ProjectReviewTag> tagSet = projectReviewTagRepository.findAllByProjectReviewId(projectReview.getId());
//        null 예외처리... -> null이면 싹 다 삭제
        if (tags == null) {
            projectReview.deleteProjectReviewTags((List<ProjectReviewTag>) tagSet);
            return projectReview;
        } else {
            List<ProjectReviewTag> tagsDelete = new ArrayList<>();
            for (ProjectReviewTag tag : tagSet) {
                if (!tags.contains(tag.getTag())) {
                    tagsDelete.add(tag);
                }
            }
            projectReview.deleteProjectReviewTags(tagsDelete);
//        삭제

            List<ProjectReviewTag> tagsAdd = new ArrayList<>();
            for (String tagName : tags) {
                if (!tagSet.stream().anyMatch(tag -> tag.getTag().equals(tagName))) {
                    ProjectReviewTag addTag = ProjectReviewTag.builder()
                            .projectReview(projectReview)
                            .tag(tagName)
                            .id(UUID.randomUUID())
                            .build();
                    tagsAdd.add(addTag);
                }
            }
            projectReview.addProjectReviewTags(tagsAdd);

            return projectReview;
        }
    }
}
