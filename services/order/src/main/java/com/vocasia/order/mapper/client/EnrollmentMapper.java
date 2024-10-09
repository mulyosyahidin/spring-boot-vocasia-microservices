package com.vocasia.order.mapper.client;

import com.vocasia.order.dto.client.EnrollmentDto;
import com.vocasia.order.entity.client.Enrollment;
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

    public static Enrollment mapToEntity(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = new Enrollment();

        enrollment.setId(enrollmentDto.getId());
        enrollment.setUserId(enrollmentDto.getUserId());
        enrollment.setOrderId(enrollmentDto.getOrderId());
        enrollment.setCourseId(enrollmentDto.getCourseId());
        enrollment.setEnrollmentDate(enrollmentDto.getEnrollmentDate());
        enrollment.setStatus(enrollmentDto.getStatus());
        enrollment.setProgressPercentage(enrollmentDto.getProgressPercentage());
        enrollment.setCompletionDate(enrollmentDto.getCompletionDate());
        enrollment.setCreatedAt(enrollmentDto.getCreatedAt());
        enrollment.setUpdatedAt(enrollmentDto.getUpdatedAt());

        return enrollment;
    }

}
