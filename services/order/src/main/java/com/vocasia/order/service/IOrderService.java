package com.vocasia.order.service;

import com.vocasia.order.entity.Order;
import com.vocasia.order.request.PlaceNewOrderRequest;
import com.vocasia.order.request.UpdatePaymentStatusRequest;

import java.util.List;

public interface IOrderService {

    Order placeNewOrder(PlaceNewOrderRequest placeNewOrderRequest);
    Order findById(Long orderId);
    Order updateOrderPaymentStatus(Order order, UpdatePaymentStatusRequest updatePaymentStatusRequest);

    List<Order> findAllByUserId(Long userId);

}
