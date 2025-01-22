package com.vocasia.order.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.instructor.InstructorStudentCourseDto;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.request.client.instructor.AssignCourseToStudentInstructorRequest;
import com.vocasia.order.service.IInstructorService;
import com.vocasia.order.service.client.InstructorFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class InstructorServiceImpl implements IInstructorService {

    private final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);

    private InstructorFeignClient instructorFeignClient;

    @Override
    public InstructorStudentCourseDto assignCourse(AssignCourseToStudentInstructorRequest assignCourseToStudentInstructorRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> instructorFeignClientAssignCourseResponseEntity = instructorFeignClient.assignCourse(correlationId, assignCourseToStudentInstructorRequest);
            ResponseDto responseBody = instructorFeignClientAssignCourseResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> instructorStudentCourse = data != null ? (Map<String, Object>) data.get("instructor_student_course") : null;

            logger.debug("InstructorServiceImpl.assignCourse() $instructorStudentCourse:: {}", instructorStudentCourse);

            InstructorStudentCourseDto instructorStudentCourseDto = new InstructorStudentCourseDto();

            if (instructorStudentCourse != null) {
                instructorStudentCourseDto.setId(Long.valueOf(instructorStudentCourse.get("id").toString()));
                instructorStudentCourseDto.setInstructorStudentId(Long.valueOf(instructorStudentCourse.get("instructor_student_id").toString()));
                instructorStudentCourseDto.setCourseId(Long.valueOf(instructorStudentCourse.get("course_id").toString()));
                instructorStudentCourseDto.setOrderId(Long.valueOf(instructorStudentCourse.get("order_id").toString()));
            }

            return instructorStudentCourseDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
