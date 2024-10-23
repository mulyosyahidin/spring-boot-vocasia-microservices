package com.vocasia.payment.service;

import com.vocasia.payment.request.client.UpdateOrderPaymentStatus;

public interface IOrderService {

    void updatePaymentStatus(Long orderId, UpdateOrderPaymentStatus updateOrderPaymentStatus, String correlationId);

}
