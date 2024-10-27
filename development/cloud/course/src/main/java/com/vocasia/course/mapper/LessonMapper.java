package com.vocasia.course.mapper;

import com.vocasia.course.dto.data.LessonDto;
import com.vocasia.course.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public static LessonDto mapToDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();

        lessonDto.setId(lesson.getId());
        lessonDto.setChapterId(lesson.getChapter().getId());
        lessonDto.setTitle(lesson.getTitle());
        lessonDto.setType(lesson.getType());
        lessonDto.setNeedPreviousLesson(lesson.getNeedPreviousLesson());
        lessonDto.setIsFree(lesson.getIsFree());
        lessonDto.setContentVideoDuration(lesson.getContentVideoDuration());
        lessonDto.setContentVideoUrl(lesson.getContentVideoUrl());
        lessonDto.setContentText(lesson.getContentText());
        lessonDto.setAttachmentType(lesson.getAttachmentType());
        lessonDto.setAttachmentFileUrl(lesson.getAttachmentFileUrl());
        lessonDto.setAttachmentFileName(lesson.getAttachmentFileName());
        lessonDto.setAttachmentLink(lesson.getAttachmentLink());
        lessonDto.setAttachmentLinkName(lesson.getAttachmentLinkName());

        boolean hasAttachment = lesson.getAttachmentType() != null && !lesson.getAttachmentType().isEmpty();
        lessonDto.setHasAttachment(hasAttachment);

        lessonDto.setCreatedAt(lesson.getCreatedAt());
        lessonDto.setUpdatedAt(lesson.getUpdatedAt());

        return lessonDto;
    }

}

