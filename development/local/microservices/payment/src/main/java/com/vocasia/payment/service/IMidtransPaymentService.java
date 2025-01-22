package com.vocasia.payment.service;

import com.vocasia.payment.request.CreateOrderPaymentRequest;

public interface IMidtransPaymentService {

    String requestSnapToken(CreateOrderPaymentRequest createOrderPaymentRequest);

}
