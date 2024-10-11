package com.vocasia.course.service;

import com.vocasia.course.dto.feign.InstructorDto;

public interface IInstructorService {

    InstructorDto getInstructorById(Long id, String correlationId);

}
