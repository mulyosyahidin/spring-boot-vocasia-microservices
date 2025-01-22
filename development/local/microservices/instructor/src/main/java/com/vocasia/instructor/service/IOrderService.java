package com.vocasia.instructor.service;

import com.vocasia.instructor.dto.client.order.OrderDto;
import com.vocasia.instructor.dto.client.order.OrderItemDto;

public interface IOrderService {

    OrderDto findById(Long orderId, String correlationId);
    OrderItemDto findOrderItemById(Long orderId, Long courseId, String correlationId);

}
