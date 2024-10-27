package com.vocasia.catalog.repository;

import com.vocasia.catalog.entity.Course;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, ObjectId> {

    List<Course> findTop10ByOrderByCreatedAtDesc();
    Course findById(Long courseId);

}
