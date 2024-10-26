package com.vocasia.payment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.payment.dto.ResponseDto;
import com.vocasia.payment.exception.CustomFeignException;
import com.vocasia.payment.request.client.UpdateOrderPaymentStatus;
import com.vocasia.payment.service.IOrderService;
import com.vocasia.payment.service.client.OrderFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderFeignClient orderFeignClient;
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public void updatePaymentStatus(Long orderId, UpdateOrderPaymentStatus updateOrderPaymentStatus, String correlationId) {
        try {
            ResponseEntity<ResponseDto> orderFeignClientUpdatePaymentStatusResponseEntity = orderFeignClient.updatePaymentStatus(correlationId, orderId, updateOrderPaymentStatus);
            ResponseDto responseBody = orderFeignClientUpdatePaymentStatusResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> order = data != null ? (Map<String, Object>) data.get("order") : null;

            logger.debug("OrderServiceImpl.updatePaymentStatus() $order:: {}", order);
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
