package com.vocasia.authentication.service;

import com.vocasia.authentication.dto.feign.InstructorDto;

public interface IInstructorService {

    InstructorDto getInstructorByUserId(Long userId, String correlationId);

}
