package com.vocasia.order.service.impl;

import com.vocasia.order.entity.OrderItem;
import com.vocasia.order.repository.OrderItemRepository;
import com.vocasia.order.service.IOrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements IOrderItemService {

    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem findByOrderIdAndCourseId(Long orderId, Long courseId) {
        return orderItemRepository.findByOrderIdAndCourseId(orderId, courseId);
    }

}
