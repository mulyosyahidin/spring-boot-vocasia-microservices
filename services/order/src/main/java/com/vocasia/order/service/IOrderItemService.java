package com.vocasia.order.service;

import com.vocasia.order.entity.OrderItem;

import java.util.List;

public interface IOrderItemService {

    OrderItem findByOrderIdAndCourseId(Long orderId, Long courseId);
    List<OrderItem> findAllByOrderId(Long orderId);

}
