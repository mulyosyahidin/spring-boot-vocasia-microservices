package com.vocasia.authentication.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.dto.client.instructor.InstructorDto;
import com.vocasia.authentication.exception.CustomFeignException;
import com.vocasia.authentication.service.IInstructorService;
import com.vocasia.authentication.service.client.InstructorFeignClient;
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
    public InstructorDto findByUserId(Long userId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> instructorFeignClientFindByUserIdResponseEntity = instructorFeignClient.findByUserId(correlationId, userId);
            ResponseDto responseBody = instructorFeignClientFindByUserIdResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> instructor = data != null ? (Map<String, Object>) data.get("instructor") : null;

            logger.debug("InstructorServiceImpl.findByUserId() $instructor:: {}", instructor);

            InstructorDto instructorDto = new InstructorDto();

            if (instructor != null) {
                instructorDto.setId((Integer) instructor.get("id"));
                instructorDto.setStatus((String) instructor.get("status"));
                instructorDto.setSummary((String) instructor.get("summary"));
                instructorDto.setPhoneNumber((String) instructor.get("phone_number"));
            }

            return instructorDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
