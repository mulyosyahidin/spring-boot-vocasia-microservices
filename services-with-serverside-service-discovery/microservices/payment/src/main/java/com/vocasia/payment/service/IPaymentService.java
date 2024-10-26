package com.vocasia.payment.service;

import com.vocasia.payment.entity.Payment;
import com.vocasia.payment.request.CreateOrderPaymentRequest;

public interface IPaymentService {

    Payment save(String snapToken, CreateOrderPaymentRequest createOrderPaymentRequest);
    Payment findByOrderId(Long orderId);
    Payment findByOrderNumber(String snapToken);
    void updatePaymentStatus(Payment payment, String status);

}
