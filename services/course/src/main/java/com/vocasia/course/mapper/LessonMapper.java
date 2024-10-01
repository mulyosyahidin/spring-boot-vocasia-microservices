package com.vocasia.course.mapper;

import com.vocasia.course.dto.data.LessonDto;
import com.vocasia.course.entity.Chapter;
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
        lessonDto.setCreatedAt(lesson.getCreatedAt());
        lessonDto.setUpdatedAt(lesson.getUpdatedAt());

        return lessonDto;
    }

    public static Lesson mapToEntity(LessonDto lessonDto, Chapter chapter) {
        Lesson lesson = new Lesson();

        lesson.setTitle(lessonDto.getTitle());
        lesson.setType(lessonDto.getType());
        lesson.setNeedPreviousLesson(lessonDto.getNeedPreviousLesson());
        lesson.setIsFree(lessonDto.getIsFree());
        lesson.setContentVideoDuration(lessonDto.getContentVideoDuration());
        lesson.setContentVideoUrl(lessonDto.getContentVideoUrl());
        lesson.setContentText(lessonDto.getContentText());

        return lesson;
    }

}

