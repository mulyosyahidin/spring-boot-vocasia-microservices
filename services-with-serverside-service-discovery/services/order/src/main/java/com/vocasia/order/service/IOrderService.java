package com.vocasia.order.service;

import com.vocasia.order.entity.Order;
import com.vocasia.order.request.PlaceNewOrderRequest;
import com.vocasia.order.request.UpdatePaymentStatusRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {

    Order placeNewOrder(Long userId, PlaceNewOrderRequest placeNewOrderRequest);
    Order findById(Long orderId);
    Order updateOrderPaymentStatus(Order order, UpdatePaymentStatusRequest updatePaymentStatusRequest);

    Page<Order> findAllByUserId(Long userId, Pageable paging);
    Page<Order> findAll(Pageable paging);

}
