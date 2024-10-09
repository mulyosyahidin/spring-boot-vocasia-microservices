package com.vocasia.enrollment.mapper;

import com.netflix.discovery.converters.Auto;
import com.vocasia.enrollment.dto.client.course.CourseDto;
import com.vocasia.enrollment.dto.data.EnrollmentDto;
import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.service.ICourseService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    private static ICourseService courseService;
    private static Logger logger = LoggerFactory.getLogger(EnrollmentMapper.class);

    @Autowired
    public EnrollmentMapper(ICourseService courseService) {
        EnrollmentMapper.courseService = courseService;
    }

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

        try {
            CourseDto courseDto = courseService.getCourseById(enrollment.getCourseId());

            enrollmentDto.setCourse(courseDto);
        } catch (FeignException e) {
            logger.error(e.getMessage(), e);

            enrollmentDto.setCourse(null);
        }

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


