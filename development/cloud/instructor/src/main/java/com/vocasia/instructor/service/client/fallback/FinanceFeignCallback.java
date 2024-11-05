package com.vocasia.instructor.service.client.fallback;

import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.service.client.FinanceFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FinanceFeignCallback implements FinanceFeignClient {

    @Override
    public ResponseEntity<ResponseDto> getInstructorOverview(String correlationId, Long instructorId) {
        Map<String, Object> defaultData = new HashMap<>();

        defaultData.put("month", null);
        defaultData.put("year", null);
        defaultData.put("month_name", null);
        defaultData.put("income", null);

        return ResponseEntity.ok(new ResponseDto(true, "Gagal mendapatkan data overview keuangan", defaultData, null));
    }

}
