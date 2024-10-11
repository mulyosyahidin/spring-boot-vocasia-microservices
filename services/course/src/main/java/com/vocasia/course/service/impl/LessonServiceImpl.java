package com.vocasia.course.service.impl;

import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.exception.ResourceNotFoundException;
import com.vocasia.course.repository.LessonRepository;
import com.vocasia.course.request.lesson.StoreLessonRequest;
import com.vocasia.course.request.lesson.UpdateLessonRequest;
import com.vocasia.course.service.ILessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements ILessonService {

    private final LessonRepository lessonRepository;

    @Override
    public List<Lesson> findAllByChapterId(Long chapterId) {
        return lessonRepository.findAllByChapterId(chapterId);
    }

    @Override
    public Lesson save(Chapter chapter, StoreLessonRequest storeLessonRequest) {
        Lesson lesson = new Lesson();

        lesson.setChapter(chapter);
        lesson.setTitle(storeLessonRequest.getTitle());
        lesson.setType(storeLessonRequest.getType());
        lesson.setNeedPreviousLesson(storeLessonRequest.getNeedPreviousLesson());
        lesson.setIsFree(storeLessonRequest.getIsFree());
        lesson.setContentVideoDuration(storeLessonRequest.getContentVideoDuration());
        lesson.setContentVideoUrl(storeLessonRequest.getContentVideoUrl());
        lesson.setContentText(storeLessonRequest.getContentText());

        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson findById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Data tidak ditemukan"));
    }

    @Override
    public Lesson update(Lesson lesson, UpdateLessonRequest updateLessonRequest) {
        lesson.setTitle(updateLessonRequest.getTitle());
        lesson.setType(updateLessonRequest.getType());
        lesson.setNeedPreviousLesson(updateLessonRequest.getNeedPreviousLesson());
        lesson.setIsFree(updateLessonRequest.getIsFree());
        lesson.setContentVideoDuration(updateLessonRequest.getContentVideoDuration());
        lesson.setContentVideoUrl(updateLessonRequest.getContentVideoUrl());
        lesson.setContentText(updateLessonRequest.getContentText());

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

}
