package com.vocasia.order.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.order.dto.client.EnrollmentDto;
import com.vocasia.order.dto.client.PaymentDto;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.request.client.EnrollNewCourseRequest;
import com.vocasia.order.service.IEnrollmentService;
import com.vocasia.order.service.client.EnrollmentFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements IEnrollmentService {

    private final Logger logger = LoggerFactory.getLogger(EnrollmentServiceImpl.class);
    private final EnrollmentFeignClient enrollmentFeignClient;

    @Override
    public List<EnrollmentDto> enrollNewCourse(EnrollNewCourseRequest enrollNewCourseRequest) {
        List<EnrollmentDto> enrollmentDtos = new ArrayList<>();

        try {
            ResponseEntity<Map<String, Object>> enrollCourseResponseEntity = enrollmentFeignClient.enrollCourse(enrollNewCourseRequest);
            logger.info("enrollCourseResponseEntity: {}", enrollCourseResponseEntity);

            Map<String, Object> responseBody = enrollCourseResponseEntity.getBody();

            if (responseBody != null && responseBody.get("success") != null && (Boolean) responseBody.get("success")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

                if (data != null) {
                    List<Map<String, Object>> enrollments = (List<Map<String, Object>>) data.get("enrollments");

                    if (enrollments != null) {
                        for (Map<String, Object> enrollment : enrollments) {
                            EnrollmentDto enrollmentDto = new EnrollmentDto();

                            enrollmentDto.setId(Long.valueOf(enrollment.get("id").toString()));
                            enrollmentDto.setUserId(Long.valueOf(enrollment.get("user_id").toString()));
                            enrollmentDto.setOrderId(Long.valueOf(enrollment.get("order_id").toString()));
                            enrollmentDto.setCourseId(Long.valueOf(enrollment.get("course_id").toString()));
                            enrollmentDto.setEnrollmentDate(LocalDateTime.parse((CharSequence) enrollment.get("enrollment_date")));
                            enrollmentDto.setStatus((String) enrollment.get("status"));
                            enrollmentDto.setProgressPercentage(BigDecimal.valueOf(Double.parseDouble(enrollment.get("progress_percentage").toString())));
                            enrollmentDto.setCompletionDate((LocalDateTime) enrollment.get("completion_date"));
                            enrollmentDto.setCreatedAt(LocalDateTime.parse((String) enrollment.get("created_at")));
                            enrollmentDto.setUpdatedAt(LocalDateTime.parse((String) enrollment.get("updated_at")));

                            enrollmentDtos.add(enrollmentDto);
                        }
                    }

                    return enrollmentDtos;
                }
            } else {
                logger.warn("createOrderPaymentResponseEntity: {}", responseBody != null ? responseBody.get("message") : "No response body");
            }

            return null;
        } catch (FeignException e) {
            logger.error(e.getMessage(), e);

            try {
                Map<String, Object> responseBody = new ObjectMapper().readValue(e.contentUTF8(), new TypeReference<Map<String, Object>>() {});
                Object errors = responseBody.get("errors");

                throw new CustomFeignException("Validation error", errors);
            } catch (IOException ioException) {
                throw new RuntimeException("Failed to parse error response", ioException);
            }
        }
    }

}
