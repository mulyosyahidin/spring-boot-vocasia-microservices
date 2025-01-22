package com.vocasia.order.repository;

import com.vocasia.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserIdOrderByCreatedAtDesc(Long userId);
    Page<Order> findAllByUserIdOrderByCreatedAtDesc(Long userId, Pageable paging);

    long countByPaymentStatus(String status);

    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Order o")
    double sumTotalPrice();

    @Query("SELECT COALESCE(SUM(o.totalPrice), 0.0) FROM Order o WHERE o.paymentStatus = :status")
    double sumTotalPriceByStatus(String status);

}
