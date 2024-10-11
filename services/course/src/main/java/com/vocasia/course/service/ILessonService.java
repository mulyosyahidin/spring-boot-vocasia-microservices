package com.vocasia.course.service;

import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.request.lesson.StoreLessonRequest;
import com.vocasia.course.request.lesson.UpdateLessonRequest;

import java.util.List;

public interface ILessonService {

    List<Lesson> findAllByChapterId(Long chapterId);
    Lesson save(Chapter chapter, StoreLessonRequest storeLessonRequest);
    Lesson findById(Long lessonId);
    Lesson update(Lesson lesson, UpdateLessonRequest updateLessonRequest);
    void deleteById(Lesson lesson);
    Lesson findByChapterIdAndId(Long chapterId, Long lessonId);

}
