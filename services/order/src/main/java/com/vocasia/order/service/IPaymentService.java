package com.vocasia.order.service;

import com.vocasia.order.dto.client.PaymentDto;
import com.vocasia.order.request.client.CreateOrderPaymentRequest;

public interface IPaymentService {

    PaymentDto createOrderPayment(CreateOrderPaymentRequest createOrderPaymentRequest);
    PaymentDto getPaymentDataByOrderId(Long orderId);

}
