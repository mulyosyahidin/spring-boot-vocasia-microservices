package com.vocasia.course.mapper;

import com.vocasia.course.dto.data.ChapterDto;
import com.vocasia.course.entity.Chapter;
import org.springframework.stereotype.Component;

@Component
public class ChapterMapper {

    public static ChapterDto mapToDto(Chapter chapter) {
        ChapterDto chapterDto = new ChapterDto();

        chapterDto.setId(chapter.getId());
        chapterDto.setCourseId(chapter.getCourse().getId());
        chapterDto.setTitle(chapter.getTitle());
        chapterDto.setTotalDuration(chapter.getTotalDuration());
        chapterDto.setLessonCount(chapter.getLessons().size());
        chapterDto.setCreatedAt(chapter.getCreatedAt());
        chapterDto.setUpdatedAt(chapter.getUpdatedAt());

        return chapterDto;
    }

}
