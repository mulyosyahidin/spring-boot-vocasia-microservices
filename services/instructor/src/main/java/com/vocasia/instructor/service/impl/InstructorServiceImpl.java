package com.vocasia.instructor.service.impl;

import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.repository.InstructorRepository;
import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.service.IInstructorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstructorServiceImpl implements IInstructorService {

    private InstructorRepository instructorRepository;

    @Override
    public Instructor registerNewInstructor(Long registeredUserId, RegisterRequest registerRequest) {
        Instructor instructor = new Instructor();

        instructor.setUserId(registeredUserId);
        instructor.setSummary(registerRequest.getSummary());
        instructor.setPhoneNumber(registerRequest.getPhoneNumber());

        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

    @Override
    public Instructor getInstructorByUserId(Long id) {
        return instructorRepository.findByUserId(id);
    }
}
