package com.vocasia.payment.service;

import com.vocasia.payment.entity.Payment;
import com.vocasia.payment.request.CreateOrderPaymentRequest;

public interface IPaymentService {

    Payment createPayment(String snapToken, CreateOrderPaymentRequest createOrderPaymentRequest);
    Payment getPaymentByOrderId(Long orderId);
    Payment getByOrderNumber(String snapToken);
    void updatePaymentStatus(Payment payment, String status);

}
