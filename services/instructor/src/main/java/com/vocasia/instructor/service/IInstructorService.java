package com.vocasia.instructor.service;

import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.request.UpdateProfileRequest;

public interface IInstructorService {

    Instructor registerNewInstructor(Long registeredUserId, RegisterRequest registerRequest);
    Instructor getInstructorById(Long id);
    Instructor getInstructorByUserId(Long userId);
    Instructor updateProfile(Long instructorId, UpdateProfileRequest updateProfileRequest);

}
