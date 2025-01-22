package com.vocasia.order.repository;

import com.vocasia.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem findByOrderIdAndCourseId(Long orderId, Long courseId);
    List<OrderItem> findAllByOrderId(Long orderId);

}
