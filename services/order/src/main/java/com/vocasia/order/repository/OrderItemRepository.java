package com.vocasia.order.repository;

import com.vocasia.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem findByOrderIdAndCourseId(Long orderId, Long courseId);

}
