package com.vocasia.instructor.repository;

import com.vocasia.instructor.entity.InstructorStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorStudentRepository extends JpaRepository<InstructorStudent, Long> {

    boolean existsByInstructorIdAndUserId(Long instructorId, Long userId);
    int countByInstructorId(Long instructorId);

    InstructorStudent findByInstructorIdAndUserId(Long instructorId, Long userId);

    List<InstructorStudent> findAllByInstructorId(Long instructorId);

    Page<InstructorStudent> findAllByInstructorId(Long instructorId, Pageable paging);


}
