package com.vocasia.payment.repository;

import com.vocasia.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByOrderId(Long orderId);
    Payment findByOrderNumber(String orderNumber);
}
