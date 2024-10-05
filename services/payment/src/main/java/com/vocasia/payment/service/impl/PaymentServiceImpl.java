package com.vocasia.payment.service.impl;

import com.vocasia.payment.entity.Payment;
import com.vocasia.payment.repository.PaymentRepository;
import com.vocasia.payment.request.CreateOrderPaymentRequest;
import com.vocasia.payment.service.IPaymentService;
import com.vocasia.payment.types.PaymentStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(String snapToken, CreateOrderPaymentRequest createOrderPaymentRequest) {
        Payment payment = new Payment();

        LocalDateTime paymentExpireAt = LocalDateTime.now().plusDays(1);

        payment.setOrderId(createOrderPaymentRequest.getOrderId());
        payment.setOrderNumber(createOrderPaymentRequest.getOrderNumber());
        payment.setTotalPrice(createOrderPaymentRequest.getTotalPrice());
        payment.setAdditionalFee(0.0);
        payment.setTotalPayment(createOrderPaymentRequest.getTotalPrice());
        payment.setSnapToken(snapToken);
        payment.setPaymentStatus(PaymentStatus.PENDING.name());
        payment.setPaymentExpireAt(paymentExpireAt);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    @Override
    public Payment getByOrderNumber(String orderNumber) {
        return paymentRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public void updatePaymentStatus(Payment payment, String status) {
        payment.setPaymentStatus(status);

        paymentRepository.save(payment);
    }
}
