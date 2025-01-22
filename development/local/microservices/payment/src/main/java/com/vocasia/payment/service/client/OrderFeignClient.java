package com.vocasia.payment.service.client;

import com.vocasia.payment.dto.ResponseDto;
import com.vocasia.payment.request.client.UpdateOrderPaymentStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "order")
public interface OrderFeignClient {

    @PutMapping("/api/update-payment-status/{orderId}")
    public ResponseEntity<ResponseDto> updatePaymentStatus(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                           @PathVariable Long orderId, @RequestBody UpdateOrderPaymentStatus updateOrderPaymentStatus);

}
