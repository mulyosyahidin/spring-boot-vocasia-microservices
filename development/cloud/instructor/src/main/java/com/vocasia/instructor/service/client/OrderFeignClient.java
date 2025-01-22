package com.vocasia.instructor.service.client;

import com.vocasia.instructor.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "order", url = "http://order:8008")
public interface OrderFeignClient {

    @GetMapping("/api/instructor/orders/{orderId}")
    public ResponseEntity<ResponseDto> findById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                @PathVariable("orderId") Long courseId);


    @GetMapping("/api/get-item-data/{orderId}/{courseId}")
    public ResponseEntity<ResponseDto> findOrderItemById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                         @PathVariable("orderId") Long orderId,
                                                         @PathVariable("courseId") Long courseId);

}
