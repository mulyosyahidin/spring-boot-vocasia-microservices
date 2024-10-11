package com.vocasia.order.service;

import com.vocasia.order.dto.client.payment.PaymentDto;
import com.vocasia.order.request.client.order.CreateOrderPaymentRequest;

public interface IPaymentService {

    PaymentDto createOrderPayment(CreateOrderPaymentRequest createOrderPaymentRequest, String correlationId);
    PaymentDto getPaymentDataByOrderId(Long orderId, String correlationId);

}
