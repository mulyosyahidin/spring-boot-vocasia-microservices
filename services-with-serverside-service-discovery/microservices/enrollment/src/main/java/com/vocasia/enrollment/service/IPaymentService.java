package com.vocasia.enrollment.service;

import com.vocasia.enrollment.dto.client.payment.PaymentDto;

public interface IPaymentService {

    PaymentDto findPaymentByOrderId(Long orderId, String correlationId);

}
