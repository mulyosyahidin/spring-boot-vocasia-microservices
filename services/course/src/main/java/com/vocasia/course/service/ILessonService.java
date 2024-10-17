package com.vocasia.course.service;

import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.request.lesson.StoreLessonRequest;
import com.vocasia.course.request.lesson.UpdateLessonRequest;

import java.io.IOException;
import java.util.List;

public interface ILessonService {

    List<Lesson> findAllByChapterId(Long chapterId);
    Lesson save(Chapter chapter, StoreLessonRequest storeLessonRequest) throws IOException;
    Lesson findById(Long lessonId);
    Lesson update(Lesson lesson, UpdateLessonRequest updateLessonRequest) throws IOException;
    void deleteById(Lesson lesson);
    Lesson findByChapterIdAndId(Long chapterId, Long lessonId);

}
