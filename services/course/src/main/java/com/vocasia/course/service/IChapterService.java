package com.vocasia.course.service;

import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.request.CreateChapterRequest;
import com.vocasia.course.request.UpdateChapterRequest;

import java.util.List;

public interface IChapterService {

    List<Chapter> findAllByCourseId(Course course);
    Chapter save(Course course, CreateChapterRequest createChapterRequest);
    Chapter findById(Long chapterId);
    Chapter update(Chapter chapter, UpdateChapterRequest createChapterRequest);
    void deleteById(Chapter chapter);

}
