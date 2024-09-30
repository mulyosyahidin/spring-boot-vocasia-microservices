package com.vocasia.course.mapper;

import com.vocasia.course.dto.data.ChapterDto;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class ChapterMapper {

    public static ChapterDto mapToDto(Chapter chapter) {
        ChapterDto chapterDto = new ChapterDto();
        Course course = chapter.getCourse();

        chapterDto.setId(chapter.getId());
        chapterDto.setCourse(CourseMapper.mapToDto(course));
        chapterDto.setTitle(chapter.getTitle());
        chapterDto.setTotalDuration(chapter.getTotalDuration());
        chapterDto.setCreatedAt(chapter.getCreatedAt());
        chapterDto.setUpdatedAt(chapter.getUpdatedAt());

        if (chapter.getCourse() != null) {
            chapterDto.setCourseId(chapter.getCourse().getId());
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
