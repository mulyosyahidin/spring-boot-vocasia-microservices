package com.vocasia.instructor.service;

import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.request.UpdateProfileRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInstructorService {

    Instructor registerNewInstructor(Long registeredUserId, RegisterRequest registerRequest);
    Instructor getInstructorByUserId(Long userId);
    Instructor updateProfile(Long instructorId, UpdateProfileRequest updateProfileRequest);
    Instructor findById(Long id);

    Page<Instructor> findAll(Pageable paging);
    Page<Instructor> findAllByStatus(String status, Pageable paging);

    List<Instructor> findAllByStatus(String status);
    void save(Instructor instructor);

}
