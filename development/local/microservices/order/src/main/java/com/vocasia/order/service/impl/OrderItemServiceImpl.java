package com.vocasia.order.service.impl;

import com.vocasia.order.entity.OrderItem;
import com.vocasia.order.repository.OrderItemRepository;
import com.vocasia.order.service.IOrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements IOrderItemService {

    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem findByOrderIdAndCourseId(Long orderId, Long courseId) {
        return orderItemRepository.findByOrderIdAndCourseId(orderId, courseId);
    }

    @Override
    public List<OrderItem> findAllByOrderId(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }

}
