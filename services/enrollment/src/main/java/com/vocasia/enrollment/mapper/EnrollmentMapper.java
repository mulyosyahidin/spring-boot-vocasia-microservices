package com.vocasia.enrollment.mapper;

import com.vocasia.enrollment.dto.data.EnrollmentDto;
import com.vocasia.enrollment.entity.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public static EnrollmentDto mapToDto(Enrollment enrollment) {
        EnrollmentDto enrollmentDto = new EnrollmentDto();

        enrollmentDto.setId(enrollment.getId());
        enrollmentDto.setUserId(enrollment.getUserId());
        enrollmentDto.setOrderId(enrollment.getOrderId());
        enrollmentDto.setCourseId(enrollment.getCourseId());
        enrollmentDto.setEnrollmentDate(enrollment.getEnrollmentDate());
        enrollmentDto.setStatus(enrollment.getStatus());
        enrollmentDto.setProgressPercentage(enrollment.getProgressPercentage());
        enrollmentDto.setCompletionDate(enrollment.getCompletionDate());
        enrollmentDto.setCreatedAt(enrollment.getCreatedAt());
        enrollmentDto.setUpdatedAt(enrollment.getUpdatedAt());

        return enrollmentDto;
    }

}


