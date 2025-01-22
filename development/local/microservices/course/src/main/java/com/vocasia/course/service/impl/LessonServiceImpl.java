package com.vocasia.course.service.impl;

import com.vocasia.course.config.AwsConfigProperties;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.exception.ResourceNotFoundException;
import com.vocasia.course.packages.aws.service.IAwsService;
import com.vocasia.course.repository.LessonRepository;
import com.vocasia.course.request.lesson.StoreLessonRequest;
import com.vocasia.course.request.lesson.UpdateLessonRequest;
import com.vocasia.course.service.ILessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements ILessonService {

    private final AwsConfigProperties awsConfigProperties;
    private final IAwsService awsService;

    private final LessonRepository lessonRepository;

    @Override
    public List<Lesson> findAllByChapterId(Long chapterId) {
        return lessonRepository.findAllByChapterId(chapterId);
    }

    @Override
    public Lesson save(Chapter chapter, StoreLessonRequest storeLessonRequest) throws IOException {
        Lesson lesson = new Lesson();

        lesson.setChapter(chapter);
        lesson.setTitle(storeLessonRequest.getTitle());
        lesson.setType(storeLessonRequest.getType());
        lesson.setNeedPreviousLesson(storeLessonRequest.getNeedPreviousLesson());
        lesson.setIsFree(storeLessonRequest.getIsFree());
        lesson.setContentVideoDuration(storeLessonRequest.getContentVideoDuration());
        lesson.setContentVideoUrl(storeLessonRequest.getContentVideoUrl());
        lesson.setContentText(storeLessonRequest.getContentText());

        if (Objects.equals(storeLessonRequest.getAttachmentType(), "file") && storeLessonRequest.getAttachmentFile() != null) {
            String bucketName = awsConfigProperties.getS3().getBucket();
            MultipartFile attachmentFile = storeLessonRequest.getAttachmentFile();

            String _fileName = StringUtils.cleanPath(Objects.requireNonNull(attachmentFile.getOriginalFilename()));

            String timeInMillis = String.valueOf(System.currentTimeMillis());
            String fileName = "lesson-attachment" + "-" + timeInMillis + "-" + StringUtils.cleanPath(Objects.requireNonNull(attachmentFile.getOriginalFilename()));

            String contentType = attachmentFile.getContentType();
            long fileSize = attachmentFile.getSize();
            InputStream inputStream = attachmentFile.getInputStream();

            awsService.uploadFile(bucketName, fileName, fileSize, contentType, inputStream);

            lesson.setAttachmentType(storeLessonRequest.getAttachmentType());
            lesson.setAttachmentFileName(!Objects.equals(storeLessonRequest.getAttachmentFileName(), "") ? storeLessonRequest.getAttachmentFileName() : _fileName);
            lesson.setAttachmentFileUrl(awsService.getFileUrl(bucketName, fileName));
        } else if (Objects.equals(storeLessonRequest.getAttachmentType(), "link")) {
            lesson.setAttachmentType(storeLessonRequest.getAttachmentType());
            lesson.setAttachmentLink(storeLessonRequest.getAttachmentLink());
            lesson.setAttachmentLinkName(storeLessonRequest.getAttachmentLinkName());
        }

        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson findById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Data tidak ditemukan"));
    }

    @Override
    public Lesson update(Lesson lesson, UpdateLessonRequest updateLessonRequest) throws IOException {
        lesson.setTitle(updateLessonRequest.getTitle());
        lesson.setType(updateLessonRequest.getType());
        lesson.setNeedPreviousLesson(updateLessonRequest.getNeedPreviousLesson());
        lesson.setIsFree(updateLessonRequest.getIsFree());
        lesson.setContentVideoDuration(updateLessonRequest.getContentVideoDuration());
        lesson.setContentVideoUrl(updateLessonRequest.getContentVideoUrl());
        lesson.setContentText(updateLessonRequest.getContentText());

        // Handle removeAttachment
        if (updateLessonRequest.getRemoveAttachment() != null && updateLessonRequest.getRemoveAttachment()) {
            if (Objects.equals(lesson.getAttachmentType(), "file") && lesson.getAttachmentFileUrl() != null) {
                String bucketName = awsConfigProperties.getS3().getBucket();
                String fileName = lesson.getAttachmentFileUrl().substring(lesson.getAttachmentFileUrl().lastIndexOf("/") + 1);

                awsService.deleteFile(bucketName, fileName);
            }

            lesson.setAttachmentType(null);
            lesson.setAttachmentFileName(null);
            lesson.setAttachmentFileUrl(null);
            lesson.setAttachmentLink(null);
            lesson.setAttachmentLinkName(null);
        } else {
            // Handle attachment update for file type
            if (Objects.equals(updateLessonRequest.getAttachmentType(), "file") && updateLessonRequest.getAttachmentFile() != null) {
                String bucketName = awsConfigProperties.getS3().getBucket();
                MultipartFile attachmentFile = updateLessonRequest.getAttachmentFile();

                String _fileName = StringUtils.cleanPath(Objects.requireNonNull(attachmentFile.getOriginalFilename()));
                String timeInMillis = String.valueOf(System.currentTimeMillis());
                String fileName = "lesson-attachment" + "-" + timeInMillis + "-" + StringUtils.cleanPath(Objects.requireNonNull(attachmentFile.getOriginalFilename()));

                String contentType = attachmentFile.getContentType();
                long fileSize = attachmentFile.getSize();
                InputStream inputStream = attachmentFile.getInputStream();

                // Delete the previous file if it exists
                if (Objects.equals(lesson.getAttachmentType(), "file") && lesson.getAttachmentFileUrl() != null) {
                    String previousFileName = lesson.getAttachmentFileUrl().substring(lesson.getAttachmentFileUrl().lastIndexOf("/") + 1);
                    awsService.deleteFile(bucketName, previousFileName);
                }

                // Upload new file
                awsService.uploadFile(bucketName, fileName, fileSize, contentType, inputStream);

                lesson.setAttachmentType(updateLessonRequest.getAttachmentType());
                lesson.setAttachmentFileName(!Objects.equals(updateLessonRequest.getAttachmentFileName(), "") ? updateLessonRequest.getAttachmentFileName() : _fileName);
                lesson.setAttachmentFileUrl(awsService.getFileUrl(bucketName, fileName));
            } else if (Objects.equals(updateLessonRequest.getAttachmentType(), "link")) {
                // Handle attachment update for link type
                lesson.setAttachmentType(updateLessonRequest.getAttachmentType());
                lesson.setAttachmentLink(updateLessonRequest.getAttachmentLink());
                lesson.setAttachmentLinkName(updateLessonRequest.getAttachmentLinkName());
            }
        }

        return lessonRepository.save(lesson);
    }

    @Override
    public void deleteById(Lesson lesson) {
        lessonRepository.delete(lesson);
    }

    @Override
    public Lesson findByChapterIdAndId(Long chapterId, Long lessonId) {
        Lesson lesson = lessonRepository.findByChapterIdAndId(chapterId, lessonId);

        if (lesson == null) {
            throw new ResourceNotFoundException("Data tidak ditemukan");
        }

        return lesson;
    }

    @Override
    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

}
