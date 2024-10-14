package com.vocasia.instructor.service.impl;

import com.vocasia.authentication.exception.ResourceNotFoundException;
import com.vocasia.instructor.entity.InstructorStudent;
import com.vocasia.instructor.repository.InstructorStudentRepository;
import com.vocasia.instructor.request.RegisterStudentRequest;
import com.vocasia.instructor.service.IInstructorStudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstructorStudentServiceImpl implements IInstructorStudentService {

    private InstructorStudentRepository instructorStudentRepository;

    @Override
    public boolean isStudentRegistered(RegisterStudentRequest registerStudentRequest) {
        Long instructorId = registerStudentRequest.getInstructorId();
        Long userId = registerStudentRequest.getUserId();

        return instructorStudentRepository.existsByInstructorIdAndUserId(instructorId, userId);
    }

    @Override
    public InstructorStudent registerStudent(RegisterStudentRequest registerStudentRequest) {
        InstructorStudent instructorStudent = new InstructorStudent();

        instructorStudent.setInstructorId(registerStudentRequest.getInstructorId());
        instructorStudent.setUserId(registerStudentRequest.getUserId());

        return instructorStudentRepository.save(instructorStudent);
    }

    @Override
    public InstructorStudent findStudentByUserId(Long instructorId, Long userId) {
        return instructorStudentRepository.findByInstructorIdAndUserId(instructorId, userId);
    }

    @Override
    public List<InstructorStudent> findAllByInstructorId(Long instructorId) {
        return instructorStudentRepository.findAllByInstructorId(instructorId);
    }

    @Override
    public InstructorStudent findById(Long instructorStudentId) {
        return instructorStudentRepository.findById(instructorStudentId)
                .orElseThrow(() -> new ResourceNotFoundException("Data tidak ditemukan"));
    }

    @Override
    public Page<InstructorStudent> findAllByInstructorId(Long instructorId, Pageable paging) {
        return instructorStudentRepository.findAllByInstructorId(instructorId, paging);
    }

}
