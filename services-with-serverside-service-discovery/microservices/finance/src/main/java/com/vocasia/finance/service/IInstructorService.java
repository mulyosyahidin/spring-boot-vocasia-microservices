package com.vocasia.finance.service;

import com.vocasia.finance.dto.client.instructor.InstructorDto;

public interface IInstructorService {

    InstructorDto findById(Long id, String correlationId);

}
