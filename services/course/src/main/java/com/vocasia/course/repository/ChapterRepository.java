package com.vocasia.course.repository;

import com.vocasia.course.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    List<Chapter> findAllByCourseId(Long courseId);

}
