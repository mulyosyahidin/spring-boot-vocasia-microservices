package com.vocasia.order.service;

import com.vocasia.order.dto.client.payment.PaymentDto;
import com.vocasia.order.request.client.payment.CreateOrderPaymentRequest;

public interface IPaymentService {

    PaymentDto saveOrderPayment(CreateOrderPaymentRequest createOrderPaymentRequest, String correlationId);
    PaymentDto findByOrderId(Long orderId, String correlationId);

}
