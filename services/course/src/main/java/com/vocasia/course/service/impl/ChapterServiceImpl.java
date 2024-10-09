package com.vocasia.course.service.impl;

import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.exception.ResourceNotFoundException;
import com.vocasia.course.repository.ChapterRepository;
import com.vocasia.course.request.CreateChapterRequest;
import com.vocasia.course.request.UpdateChapterRequest;
import com.vocasia.course.service.IChapterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements IChapterService {

    private final ChapterRepository chapterRepository;

    @Override
    public List<Chapter> index(Long courseId) {
        return chapterRepository.findAllByCourseId(courseId);
    }

    @Override
    public Chapter store(Course course, CreateChapterRequest createChapterRequest) {
        Chapter chapter = new Chapter();

        chapter.setCourse(course);
        chapter.setTitle(createChapterRequest.getTitle());
        chapter.setTotalDuration(createChapterRequest.getTotalDuration());

        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter show(Long chapterId) {
        return chapterRepository.findById(chapterId)
                .orElseThrow(() -> new ResourceNotFoundException("Data tidak ditemukan"));
    }

    @Override
    public Chapter update(Chapter chapter, UpdateChapterRequest createChapterRequest) {
        chapter.setTitle(createChapterRequest.getTitle());
        chapter.setTotalDuration(createChapterRequest.getTotalDuration());

        return chapterRepository.save(chapter);
    }

    @Override
    public void delete(Chapter chapter) {
        chapterRepository.delete(chapter);
    }
}
