package com.vocasia.order.service.client;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.request.client.CreateOrderPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "payment")
public interface PaymentFeignClient {

    @PostMapping("/api/create-order-payment")
    public ResponseEntity<ResponseDto> createOrderPayment(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @RequestBody CreateOrderPaymentRequest createOrderPaymentRequest);

    @GetMapping("/api/payment-data-by-order-id/{orderId}")
    public ResponseEntity<ResponseDto> getPaymentDataByOrderId(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                               @PathVariable Long orderId);

}
