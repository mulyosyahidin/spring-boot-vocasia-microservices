package com.vocasia.instructor.repository;

import com.vocasia.instructor.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Instructor findByUserId(Long id);

}
