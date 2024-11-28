package com.vocasia.order.service.client;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.request.client.finance.NewInstructorBalanceHistoryRequest;
import com.vocasia.order.request.client.finance.NewInstructorIncomeRequest;
import com.vocasia.order.request.client.finance.NewPlatformBalanceHistoryRequest;
import com.vocasia.order.request.client.finance.NewPlatformIncomeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "finance", url = "http://finance:8006")
public interface FinanceFeignClient {

    @PostMapping(value = "/api/instructor-income/store", consumes = "application/json")
    ResponseEntity<ResponseDto> saveInstructorIncome(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                            @RequestBody NewInstructorIncomeRequest newInstructorIncomeRequest);

    @PostMapping(value = "/api/platform-income/store", consumes = "application/json")
    ResponseEntity<ResponseDto> savePlatformIncome(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @RequestBody NewPlatformIncomeRequest newPlatformIncomeRequest);

    @PostMapping(value = "/api/instructor-balance/store", consumes = "application/json")
    ResponseEntity<ResponseDto> saveInstructorBalance(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                             @RequestBody NewInstructorBalanceHistoryRequest newInstructorBalanceHistoryRequest);

    @PostMapping(value = "/api/platform-balance/store", consumes = "application/json")
    ResponseEntity<ResponseDto> savePlatformBalance(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                           @RequestBody NewPlatformBalanceHistoryRequest newPlatformBalanceHistoryRequest);

    @GetMapping(value = "/api/admin/instructor-income/{orderId}")
    ResponseEntity<ResponseDto> findInstructorIncomesByOrderId(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                                     @PathVariable("orderId") Long orderId);

    @GetMapping(value = "/api/admin/platform-income/{orderId}")
    ResponseEntity<ResponseDto> findPlatformIncomesByOrderId(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                                     @PathVariable("orderId") Long orderId);

}
