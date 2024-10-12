package com.vocasia.order.service.client;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.request.client.finance.NewInstructorIncomeRequest;
import com.vocasia.order.request.client.finance.NewPlatformIncomeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "finance")
public interface FinanceFeignClient {

    @PostMapping(value = "/api/instructor-income/store", consumes = "application/json")
    public ResponseEntity<ResponseDto> saveInstructorIncome(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                            @RequestBody NewInstructorIncomeRequest newInstructorIncomeRequest);

    @PostMapping(value = "/api/platform-income/store", consumes = "application/json")
    public ResponseEntity<ResponseDto> savePlatformIncome(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @RequestBody NewPlatformIncomeRequest newPlatformIncomeRequest);

}
