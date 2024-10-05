package com.vocasia.order.mapper;

import com.vocasia.order.dto.data.OrderDto;
import com.vocasia.order.dto.data.OrderItemDto;
import com.vocasia.order.entity.Order;
import com.vocasia.order.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

        if (order.getOrderItems() != null) {
            List<OrderItemDto> orderItemDtos = order.getOrderItems().stream()
                    .map(OrderItemMapper::mapToDto)
                    .collect(Collectors.toList());
            orderDto.setOrderItems(orderItemDtos);
        }

        return orderDto;
    }

    public static Order mapToEntity(OrderDto orderDto) {
        Order order = new Order();

        order.setId(orderDto.getId());
        order.setUserId(orderDto.getUserId());
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setTotalItems(orderDto.getTotalItems());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setTotalDiscount(orderDto.getTotalDiscount());
        order.setPaymentStatus(orderDto.getPaymentStatus());
        order.setCreatedAt(LocalDateTime.parse(orderDto.getCreatedAt()));
        order.setUpdatedAt(LocalDateTime.parse(orderDto.getUpdatedAt()));

        if (orderDto.getOrderItems() != null) {
            List<OrderItem> orderItems = orderDto.getOrderItems().stream()
                    .map(OrderItemMapper::mapToEntity)
                    .collect(Collectors.toList());
            order.setOrderItems(orderItems);
        }

        return order;
    }
}
