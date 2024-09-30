package com.vocasia.course.service;

import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.request.CreateChapterRequest;
import com.vocasia.course.request.UpdateChapterRequest;

import java.util.List;

public interface IChapterService {

    Chapter createChapter(Course course, CreateChapterRequest createChapterRequest);
    List<Chapter> getChaptersByCourseId(Long courseId);
    Chapter updateChapter(Long chapterId, UpdateChapterRequest createChapterRequest);
    Chapter findById(Long chapterId);
    void deleteChapterById(Long chapterId);

}
