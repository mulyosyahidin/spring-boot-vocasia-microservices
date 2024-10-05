package com.vocasia.order.service.client;

import com.vocasia.order.request.PlaceNewOrderRequest;
import com.vocasia.order.request.client.CreateOrderPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "payment")
public interface PaymentFeignClient {

    @PostMapping("/api/create-order-payment")
    public ResponseEntity<Map<String, Object>> createOrderPayment(@RequestBody CreateOrderPaymentRequest createOrderPaymentRequest);

    @GetMapping("/api/payment-data-by-order-id/{orderId}")
    public ResponseEntity<Map<String, Object>> getPaymentDataByOrderId(@PathVariable Long orderId);

}
