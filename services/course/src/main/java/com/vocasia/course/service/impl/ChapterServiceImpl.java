package com.vocasia.course.service.impl;

import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
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
    public Chapter createChapter(Course course, CreateChapterRequest createChapterRequest) {
        Chapter chapter = new Chapter();

        chapter.setCourse(course);
        chapter.setTitle(createChapterRequest.getTitle());
        chapter.setTotalDuration(createChapterRequest.getTotalDuration());

        return chapterRepository.save(chapter);
    }

    @Override
    public List<Chapter> getChaptersByCourseId(Long courseId) {
        return chapterRepository.findAllByCourseId(courseId);
    }

    @Override
    public Chapter updateChapter(Long chapterId, UpdateChapterRequest createChapterRequest) {
        Chapter chapter = chapterRepository.findById(chapterId).orElseThrow(() -> new RuntimeException("Chapter not found"));

        chapter.setTitle(createChapterRequest.getTitle());
        chapter.setTotalDuration(createChapterRequest.getTotalDuration());

        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter findById(Long chapterId) {
        return chapterRepository.findById(chapterId).orElseThrow(() -> new RuntimeException("Chapter not found"));
    }

    @Override
    public void deleteChapterById(Long chapterId) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));

        chapterRepository.delete(chapter);
    }
}
