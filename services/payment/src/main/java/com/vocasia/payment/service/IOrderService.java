package com.vocasia.payment.service;

import com.vocasia.payment.dto.feign.OrderDto;
import com.vocasia.payment.request.client.UpdateOrderPaymentStatus;

public interface IOrderService {

    OrderDto updatePaymentStatus(Long orderId, UpdateOrderPaymentStatus updateOrderPaymentStatus);

}
