package com.vocasia.course.repository;

import com.vocasia.course.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByChapterId(Long chapterId);
    Lesson findByChapterIdAndId(Long chapterId, Long lessonId);

}
