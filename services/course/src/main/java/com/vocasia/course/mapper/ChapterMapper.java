package com.vocasia.course.mapper;

import com.vocasia.course.dto.data.ChapterDto;
import com.vocasia.course.dto.data.LessonDto;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.entity.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChapterMapper {

    public static ChapterDto mapToDto(Chapter chapter) {
        ChapterDto chapterDto = new ChapterDto();

        List<Lesson> lessons = chapter.getLessons();

        chapterDto.setId(chapter.getId());
        chapterDto.setCourseId(chapter.getCourse().getId());
        chapterDto.setTitle(chapter.getTitle());
        chapterDto.setTotalDuration(chapter.getTotalDuration());
        chapterDto.setCreatedAt(chapter.getCreatedAt());
        chapterDto.setUpdatedAt(chapter.getUpdatedAt());

        if (lessons != null) {
            chapterDto.setLessons(lessons.stream().map(LessonMapper::mapToDto).toList());
        }

        return chapterDto;
    }

    public static Chapter mapToEntity(ChapterDto chapterDto, Course course) {
        Chapter chapter = new Chapter();

        chapter.setCourse(course);
        chapter.setTitle(chapterDto.getTitle());
        chapter.setTotalDuration(chapterDto.getTotalDuration());

        return chapter;
    }
}
