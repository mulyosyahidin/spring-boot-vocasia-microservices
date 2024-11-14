package com.vocasia.instructor.repository;

import com.vocasia.instructor.entity.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Instructor findByUserId(Long id);
    List<Instructor> findAllByStatus(String status);

    Page<Instructor> findAllByStatus(String status, Pageable paging);

    long countByStatus(String status);

}
