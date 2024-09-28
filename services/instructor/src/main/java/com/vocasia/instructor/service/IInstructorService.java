package com.vocasia.instructor.service;

import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.request.RegisterRequest;

public interface IInstructorService {

    Instructor registerNewInstructor(Long registeredUserId, RegisterRequest registerRequest);

}
