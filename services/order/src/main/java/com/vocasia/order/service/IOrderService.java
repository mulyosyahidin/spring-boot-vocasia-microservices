package com.vocasia.order.service;

import com.vocasia.order.entity.Order;
import com.vocasia.order.request.PlaceNewOrderRequest;
import com.vocasia.order.request.UpdatePaymentStatusRequest;

public interface IOrderService {

    Order placeNewOrder(PlaceNewOrderRequest placeNewOrderRequest);
    Order getOrderById(Long orderId);
    Order updatePaymentStatus(Long orderId, UpdatePaymentStatusRequest updatePaymentStatusRequest);

}
