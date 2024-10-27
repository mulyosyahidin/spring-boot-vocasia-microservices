package com.vocasia.order.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.request.client.enrollment.EnrollNewCourseRequest;
import com.vocasia.order.service.IEnrollmentService;
import com.vocasia.order.service.client.EnrollmentFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements IEnrollmentService {

    private final Logger logger = LoggerFactory.getLogger(EnrollmentServiceImpl.class);

    private final EnrollmentFeignClient enrollmentFeignClient;

    @Override
    public void enrollNewCourse(EnrollNewCourseRequest enrollNewCourseRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> enrollmentFeignClientEnrollCourseResponseEntity = enrollmentFeignClient.enrollCourse(correlationId, enrollNewCourseRequest);
        }  catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
