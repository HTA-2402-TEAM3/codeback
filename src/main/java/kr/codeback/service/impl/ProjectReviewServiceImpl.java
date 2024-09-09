package kr.codeback.service.impl;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.transaction.Transactional;
import kr.codeback.model.dto.request.review.ProjectReviewRequestDTO;
import kr.codeback.model.dto.response.review.CodeReviewListResponseDTO;
import kr.codeback.model.dto.response.review.ProjectReviewListResponseDTO;
import kr.codeback.model.dto.response.review.ProjectReviewResponseDTO;
import kr.codeback.model.entity.*;
import kr.codeback.repository.ProjectReviewCommentRepository;
import kr.codeback.repository.ProjectReviewImageRepository;
import kr.codeback.repository.ProjectReviewRepository;
import kr.codeback.service.S3Service;
import kr.codeback.service.interfaces.CodeReviewPreferenceService;
import kr.codeback.service.interfaces.ProjectReviewCommentService;
import kr.codeback.service.interfaces.ProjectReviewImageService;
import kr.codeback.service.interfaces.ProjectReviewTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.codeback.service.interfaces.ProjectReviewService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectReviewServiceImpl implements ProjectReviewService {
    private final ProjectReviewRepository projectReviewRepository;
    private final ProjectReviewImageRepository projectReviewImageRepository;
    private final ProjectReviewCommentService projectReviewCommentService;

    private final ProjectReviewImageService projectReviewImageService;
    private final ProjectReviewTagService projectReviewTagService;
    private final CodeReviewPreferenceService codeReviewPreferenceService;
    private final S3Service s3Service;

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
                            .projectReviewComments(projectReview.getComments().size())
                            .build();
                });
    }

    @Override
    public ProjectReview findById(UUID projectID) {
        Optional<ProjectReview> projectReview = projectReviewRepository.findById(projectID);
        return projectReview.orElseThrow(()->new IllegalArgumentException("no projectReview... :" +projectID));
    }

//    TEST TEST TEST TEST TEST TEST TEST
//    public ProjectReview uploadFiles(List<MultipartFile> multipartFiles) throws IOException {
//        List<String> imageUrlList = new ArrayList<>();
//        Set<ProjectReviewImage> projectReviewImages = new LinkedHashSet<>();;
//        ProjectReview projectReview = projectReviewRepository.findById(UUID.fromString("ea4c48e4-6b58-11ef-8e2a-0242ac140002"))
//                .orElseThrow(()->new IllegalArgumentException("no projectReview"));
//
//        for(MultipartFile multipartFile : multipartFiles) {
//            String fileName = UUID.randomUUID().toString().substring(0,4) + multipartFile.getOriginalFilename();
//            String url = s3Service.upload(multipartFile, fileName);
//            imageUrlList.add(fileName);
//
//            ProjectReviewImage projectReviewImage = ProjectReviewImage.builder()
//                    .fileName(fileName)
//                    .id(UUID.randomUUID())
//                    .projectReview(projectReview)
//                    .url(url)
//                    .build();
//
//            projectReviewImageRepository.save(projectReviewImage);
//
//            projectReviewImages.add(projectReviewImage);
//        }
//        return projectReview;
//    }

    @Override
    @Transactional
    public ProjectReview save(Member member, ProjectReviewRequestDTO pjRequestDTO) throws IOException {
        ProjectReview reviewObj = ProjectReview.builder()
                .id(UUID.randomUUID())
                .member(member)
                .title(pjRequestDTO.getTitle())
                .content(pjRequestDTO.getContent())
                .githubURL(pjRequestDTO.getGithubUrl())
                .build();
        projectReviewRepository.save(reviewObj);

        Set<ProjectReviewImage> imageSet = projectReviewImageService.save(pjRequestDTO.getImageFiles(), reviewObj);
        Set<ProjectReviewTag> tagSet = projectReviewTagService.save(pjRequestDTO.getTags(), reviewObj);

        reviewObj.addSet(imageSet, tagSet);

        return projectReviewRepository.save(reviewObj);
    }



    @Override
    @Transactional
    public void deleteAllById(UUID projectID) {
        projectReviewImageService.deleteAllByProjectReviewId(projectID);
        projectReviewTagService.deleteAllByProjectReviewId(projectID);
        projectReviewCommentService.deleteAllByProjectReviewId(projectID);
        projectReviewRepository.deleteById(projectID);
    }
}
