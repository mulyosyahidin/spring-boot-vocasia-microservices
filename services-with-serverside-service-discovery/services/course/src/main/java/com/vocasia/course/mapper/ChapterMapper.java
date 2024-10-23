package com.vocasia.course.mapper;

import com.vocasia.course.dto.data.ChapterDto;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChapterMapper {

    public static ChapterDto mapToDto(Chapter chapter) {
        ChapterDto chapterDto = new ChapterDto();

        chapterDto.setId(chapter.getId());
        chapterDto.setCourseId(chapter.getCourse().getId());
        chapterDto.setTitle(chapter.getTitle());
        chapterDto.setTotalDuration(chapter.getTotalDuration());

        List<Lesson> lessons = chapter.getLessons();
        chapterDto.setLessonCount(lessons != null ? lessons.size() : 0);

        chapterDto.setCreatedAt(chapter.getCreatedAt());
        chapterDto.setUpdatedAt(chapter.getUpdatedAt());

        return chapterDto;
    }

}
