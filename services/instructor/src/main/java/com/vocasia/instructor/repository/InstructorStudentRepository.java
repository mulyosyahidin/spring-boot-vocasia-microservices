package com.vocasia.instructor.repository;

import com.vocasia.instructor.entity.InstructorStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorStudentRepository extends JpaRepository<InstructorStudent, Long> {

    boolean existsByInstructorIdAndUserId(Long instructorId, Long userId);
    InstructorStudent findByInstructorIdAndUserId(Long instructorId, Long userId);
    List<InstructorStudent> findAllByInstructorId(Long instructorId);

}
