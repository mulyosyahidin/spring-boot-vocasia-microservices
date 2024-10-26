package com.vocasia.catalog.repository;

import com.vocasia.catalog.entity.Course;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, ObjectId> {
}
