package com.vocasia.course.service;

import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.request.lesson.StoreLessonRequest;
import com.vocasia.course.request.lesson.UpdateLessonRequest;

import java.util.List;

public interface ILessonService {

    List<Lesson> index(Long chapterId);
    Lesson store(Chapter chapter, StoreLessonRequest storeLessonRequest);
    Lesson show(Long lessonId);
    Lesson update(Lesson lesson, UpdateLessonRequest updateLessonRequest);
    void delete(Lesson lesson);

}
