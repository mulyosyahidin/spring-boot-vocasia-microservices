package com.vocasia.authentication.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.authentication.dto.feign.InstructorDto;
import com.vocasia.authentication.exception.CustomFeignException;
import com.vocasia.authentication.service.IInstructorService;
import com.vocasia.authentication.service.client.InstructorFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class InstructorServiceImpl implements IInstructorService {

    private InstructorFeignClient instructorFeignClient;

    private final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);

    @Override
    public InstructorDto getInstructorByUserId(Long userId, String correlationId) {
        try {
            ResponseEntity<Map<String, Object>> instructorProfileResponseEntity = instructorFeignClient.getInstructorByUserId(correlationId, userId);

            Map<String, Object> responseBody = instructorProfileResponseEntity.getBody();
            logger.info("Response Body: {}", responseBody);

            if (responseBody != null && responseBody.get("success") != null && (Boolean) responseBody.get("success")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

                if (data != null) {
                    Map<String, Object> instructor = (Map<String, Object>) data.get("instructor");
                    InstructorDto instructorDto = new InstructorDto();

                    instructorDto.setId((Integer) instructor.get("id"));
                    instructorDto.setStatus((String) instructor.get("status"));
                    instructorDto.setSummary((String) instructor.get("summary"));
                    instructorDto.setPhoneNumber((String) instructor.get("phone_number"));

                    return instructorDto;
                }
            } else {
                logger.warn("Feign failed: {}", responseBody != null ? responseBody.get("message") : "No response body");
            }

            return null;
        } catch (FeignException e) {
            throw new RuntimeException("Failed to parse error response", e);
        }
    }
}
