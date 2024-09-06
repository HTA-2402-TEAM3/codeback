package kr.codeback.service.impl;

import kr.codeback.model.dto.response.review.CodeReviewListResponseDTO;
import kr.codeback.model.dto.response.review.ProjectReviewListResponseDTO;
import kr.codeback.model.dto.response.review.ProjectReviewResponseDTO;
import kr.codeback.model.entity.*;
import kr.codeback.repository.ProjectReviewRepository;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.codeback.service.interfaces.ProjectReviewService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectReviewServiceImpl implements ProjectReviewService {
    private final ProjectReviewRepository projectReviewRepository;
    private final CodeReviewPreferenceService codeReviewPreferenceService;

    public Page<ProjectReviewListResponseDTO> findAllWithPage(int pageNum, int pageSize, String sort) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, sort));

        return projectReviewRepository.findAll(pageable)
                .map((ProjectReview projectReview) -> {
                    String thumbnailUrl = projectReview.getProjectReviewImages().stream()
                            .findFirst()
                            .map(ProjectReviewImage::getUrl)
                            .orElse(null);

                    return ProjectReviewListResponseDTO.builder()
                            .id(projectReview.getId())
                            .member(projectReview.getMember().getNickname())
                            .createDate(projectReview.getCreateDate())
                            .title(projectReview.getTitle())
                            .projectReviewTags(projectReview.getProjectReviewTags().stream().map(ProjectReviewTag::getTag).collect(Collectors.toList()))
                            .projectReviewThumbnails(thumbnailUrl) // null일 수 있음
                            .preferenceCnt(codeReviewPreferenceService.findByEntityID(projectReview.getId()).size())
                            .build();
                });
    }

    @Override
    public ProjectReview findById(UUID projectID) {
        Optional<ProjectReview> projectReview = projectReviewRepository.findById(projectID);
        return projectReview.orElseThrow(()->new IllegalArgumentException("no projectReview... :" +projectID));
    }
}
