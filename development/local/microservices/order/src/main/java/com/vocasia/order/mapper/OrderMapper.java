package com.vocasia.order.mapper;

import com.vocasia.order.dto.data.OrderDto;
import com.vocasia.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public static OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUserId());
        orderDto.setOrderNumber(order.getOrderNumber());
        orderDto.setTotalItems(order.getTotalItems());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setTotalDiscount(order.getTotalDiscount());
        orderDto.setPaymentStatus(order.getPaymentStatus());
        orderDto.setCreatedAt(order.getCreatedAt().toString());
        orderDto.setUpdatedAt(order.getUpdatedAt().toString());

        return orderDto;
    }

}
