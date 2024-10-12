package com.vocasia.order.service;

import com.vocasia.order.entity.OrderItem;

public interface IOrderItemService {

    OrderItem findByOrderIdAndCourseId(Long orderId, Long courseId);

}
