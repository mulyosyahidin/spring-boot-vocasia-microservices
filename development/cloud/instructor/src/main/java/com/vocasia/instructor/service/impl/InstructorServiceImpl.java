package com.vocasia.instructor.service.impl;

import com.vocasia.authentication.exception.ResourceNotFoundException;
import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.repository.InstructorRepository;
import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.request.UpdateProfileRequest;
import com.vocasia.instructor.service.IInstructorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Instructor getInstructorByUserId(Long id) {
        return instructorRepository.findByUserId(id);
    }

    @Override
    public Instructor updateProfile(Long instructorId, UpdateProfileRequest updateProfileRequest) {
        Instructor instructor = instructorRepository.findById(instructorId).orElse(null);

        if (instructor == null) {
            return null;
        }

        instructor.setSummary(updateProfileRequest.getSummary());
        instructor.setPhoneNumber(updateProfileRequest.getPhoneNumber());

        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor findById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data tidak ditemukan"));
    }

    @Override
    public Page<Instructor> findAll(Pageable paging) {
        return instructorRepository.findAll(paging);
    }

    @Override
    public Page<Instructor> findAllByStatus(String status, Pageable paging) {
        return instructorRepository.findAllByStatus(status, paging);
    }

    @Override
    public List<Instructor> findAllByStatus(String status) {
        return instructorRepository.findAllByStatus(status);
    }

    @Override
    public void save(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    @Override
    public long count() {
        return instructorRepository.count();
    }

    @Override
    public long countByStatus(String status) {
        return instructorRepository.countByStatus(status);
    }

}
