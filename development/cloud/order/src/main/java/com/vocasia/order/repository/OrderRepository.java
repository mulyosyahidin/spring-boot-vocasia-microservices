package com.vocasia.order.repository;

import com.vocasia.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserIdOrderByCreatedAtDesc(Long userId);
    Page<Order> findAllByUserIdOrderByCreatedAtDesc(Long userId, Pageable paging);

}
