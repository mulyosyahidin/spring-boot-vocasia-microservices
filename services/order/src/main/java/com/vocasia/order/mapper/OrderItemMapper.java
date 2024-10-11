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

}
