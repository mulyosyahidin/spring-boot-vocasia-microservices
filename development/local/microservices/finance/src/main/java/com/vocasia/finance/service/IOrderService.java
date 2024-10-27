package com.vocasia.finance.service;

import com.vocasia.finance.dto.client.order.OrderDto;
import com.vocasia.finance.dto.client.order.OrderItemDto;

public interface IOrderService {

    OrderDto findById(Long orderId, String correlationId);
    OrderItemDto findOrderItemById(Long orderId, Long courseId, String correlationId);

}
