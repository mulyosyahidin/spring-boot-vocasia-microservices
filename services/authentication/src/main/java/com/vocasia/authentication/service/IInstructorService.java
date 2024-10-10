package com.vocasia.authentication.service;

import com.vocasia.authentication.dto.client.InstructorDto;

public interface IInstructorService {

    InstructorDto getInstructorByUserId(Long userId, String correlationId);

}
