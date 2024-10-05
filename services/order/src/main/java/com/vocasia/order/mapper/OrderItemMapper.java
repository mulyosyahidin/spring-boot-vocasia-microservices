package com.vocasia.order.mapper;

import com.vocasia.order.dto.data.OrderItemDto;
import com.vocasia.order.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public static OrderItemDto mapToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrderId(orderItem.getOrder().getId());
        orderItemDto.setCourseId(orderItem.getCourseId());
        orderItemDto.setCourseTitle(orderItem.getCourseTitle());
        orderItemDto.setCourseSlug(orderItem.getCourseSlug());
        orderItemDto.setCourseFeaturedPictureUrl(orderItem.getCourseFeaturedPictureUrl());
        orderItemDto.setCoursePrice(orderItem.getCoursePrice());
        orderItemDto.setCourseIsFree(orderItem.isCourseIsFree());
        orderItemDto.setCourseIsDiscount(orderItem.isCourseIsDiscount());
        orderItemDto.setCourseDiscount(orderItem.getCourseDiscount());
        orderItemDto.setCourseSubtotal(orderItem.getCourseSubtotal());

        return orderItemDto;
    }

    public static OrderItem mapToEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();

        orderItem.setId(orderItemDto.getId());
        orderItem.setCourseId(orderItemDto.getCourseId());
        orderItem.setCourseTitle(orderItemDto.getCourseTitle());
        orderItem.setCourseSlug(orderItemDto.getCourseSlug());
        orderItem.setCourseFeaturedPictureUrl(orderItemDto.getCourseFeaturedPictureUrl());
        orderItem.setCoursePrice(orderItemDto.getCoursePrice());
        orderItem.setCourseIsFree(orderItemDto.isCourseIsFree());
        orderItem.setCourseIsDiscount(orderItemDto.isCourseIsDiscount());
        orderItem.setCourseDiscount(orderItemDto.getCourseDiscount());
        orderItem.setCourseSubtotal(orderItemDto.getCourseSubtotal());

        return orderItem;
    }
}
