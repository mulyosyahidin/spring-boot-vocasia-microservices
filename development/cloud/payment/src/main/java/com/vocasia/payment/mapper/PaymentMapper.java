package com.vocasia.payment.mapper;

import com.vocasia.payment.dto.data.PaymentDto;
import com.vocasia.payment.entity.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapper {

    public static PaymentDto mapToDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();

        paymentDto.setId(payment.getId());
        paymentDto.setOrderId(payment.getOrderId());
        paymentDto.setOrderNumber(payment.getOrderNumber());
        paymentDto.setTotalPrice(payment.getTotalPrice());
        paymentDto.setAdditionalFee(payment.getAdditionalFee());
        paymentDto.setTotalPayment(payment.getTotalPayment());
        paymentDto.setSnapToken(payment.getSnapToken());
        paymentDto.setPaymentStatus(payment.getPaymentStatus());
        paymentDto.setPaymentExpireAt(payment.getPaymentExpireAt());
        paymentDto.setIsExpired(payment.getPaymentExpireAt().isBefore(LocalDateTime.now()));
        paymentDto.setCreatedAt(payment.getCreatedAt());
        paymentDto.setUpdatedAt(payment.getUpdatedAt());

        return paymentDto;
    }

    public static Payment mapToEntity(PaymentDto paymentDto) {
        Payment payment = new Payment();

        payment.setId(paymentDto.getId());
        payment.setOrderId(paymentDto.getOrderId());
        payment.setOrderNumber(paymentDto.getOrderNumber());
        payment.setTotalPrice(paymentDto.getTotalPrice());
        payment.setAdditionalFee(paymentDto.getAdditionalFee());
        payment.setTotalPayment(paymentDto.getTotalPayment());
        payment.setSnapToken(paymentDto.getSnapToken());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setPaymentExpireAt(paymentDto.getPaymentExpireAt());
        payment.setCreatedAt(paymentDto.getCreatedAt());
        payment.setUpdatedAt(paymentDto.getUpdatedAt());

        return payment;
    }
}
