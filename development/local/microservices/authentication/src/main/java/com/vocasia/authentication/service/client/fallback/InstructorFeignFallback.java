package com.vocasia.authentication.service.client.fallback;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.service.client.InstructorFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InstructorFeignFallback implements InstructorFeignClient {

    @Override
    public ResponseEntity<ResponseDto> findByUserId(String correlationId, Long userId) {
        Map<String, Object> defaultData = new HashMap<>();

        defaultData.put("id", 0);
        defaultData.put("status", "");
        defaultData.put("summary", "");
        defaultData.put("phone_number", "");

        return ResponseEntity.ok(new ResponseDto(true, "Gagal mendapatkan data instruktur", defaultData, null));
    }

}
