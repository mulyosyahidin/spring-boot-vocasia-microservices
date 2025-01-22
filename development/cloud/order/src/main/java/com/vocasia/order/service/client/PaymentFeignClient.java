package com.vocasia.order.service.client;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.request.client.payment.CreateOrderPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "payment", url = "http://payment:8009")
public interface PaymentFeignClient {

    @PostMapping("/api/student/create-order-payment")
    ResponseEntity<ResponseDto> saveOrderPayment(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                        @RequestBody CreateOrderPaymentRequest createOrderPaymentRequest);

    @GetMapping("/api/student/orders/{orderId}")
    ResponseEntity<ResponseDto> findByOrderId(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @PathVariable Long orderId);

}
