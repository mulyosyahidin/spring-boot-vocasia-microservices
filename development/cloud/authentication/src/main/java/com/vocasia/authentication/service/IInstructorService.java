package com.vocasia.authentication.service;

import com.vocasia.authentication.dto.client.instructor.InstructorDto;

public interface IInstructorService {

    InstructorDto findByUserId(Long userId, String correlationId);

}
