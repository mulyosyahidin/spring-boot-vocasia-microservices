package com.vocasia.payment.service.impl;

import com.vocasia.payment.dto.feign.OrderDto;
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
    public OrderDto updatePaymentStatus(Long orderId, UpdateOrderPaymentStatus updateOrderPaymentStatus) {
        logger.debug("STATUS TO UPDATE: {}", updateOrderPaymentStatus.getStatus());

        try {
            ResponseEntity<Map<String, Object>> updateOrderPaymentStatusResponseEntity = orderFeignClient.updatePaymentStatus(orderId, updateOrderPaymentStatus);

            Map<String, Object> responseBody = updateOrderPaymentStatusResponseEntity.getBody();
            logger.info("Response Body: {}", responseBody);

            if (responseBody != null && responseBody.get("success") != null && (Boolean) responseBody.get("success")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

                if (data != null) {
                    Map<String, Object> instructor = (Map<String, Object>) data.get("order");
                    OrderDto orderDto = new OrderDto();

                    orderDto.setId(Long.valueOf(instructor.get("id").toString()));
                    orderDto.setUserId(Long.valueOf(instructor.get("user_id").toString()));
                    orderDto.setOrderNumber((String) instructor.get("order_number"));
                    orderDto.setTotalItems((int) instructor.get("total_items"));
                    orderDto.setTotalPrice((double) instructor.get("total_price"));
                    orderDto.setTotalDiscount((double) instructor.get("total_discount"));
                    orderDto.setPaymentStatus((String) instructor.get("payment_status"));
                    orderDto.setCreatedAt((String) instructor.get("created_at"));
                    orderDto.setUpdatedAt((String) instructor.get("updated_at"));

                    return orderDto;
                }
            } else {
                logger.warn("Feign failed: {}", responseBody != null ? responseBody.get("message") : "No response body");
            }

            return null;
        } catch (FeignException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
