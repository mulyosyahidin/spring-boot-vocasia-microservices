package com.vocasia.payment.service.client;

import com.vocasia.payment.request.client.UpdateOrderPaymentStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "order")
public interface OrderFeignClient {

    @PutMapping("/api/update-payment-status/{orderId}")
    public ResponseEntity<Map<String, Object>> updatePaymentStatus(@PathVariable Long orderId, @RequestBody UpdateOrderPaymentStatus updateOrderPaymentStatus);

}
