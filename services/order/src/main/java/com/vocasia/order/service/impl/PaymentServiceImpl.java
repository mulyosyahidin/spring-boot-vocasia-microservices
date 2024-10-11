package com.vocasia.order.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.PaymentDto;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.request.client.CreateOrderPaymentRequest;
import com.vocasia.order.service.IPaymentService;
import com.vocasia.order.service.client.PaymentFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PaymentFeignClient paymentFeignClient;

    @Override
    public PaymentDto createOrderPayment(CreateOrderPaymentRequest createOrderPaymentRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> paymentFeignClientCreateOrderPaymentResponseEntity = paymentFeignClient.createOrderPayment(correlationId, createOrderPaymentRequest);
            ResponseDto responseBody = paymentFeignClientCreateOrderPaymentResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> payment = data != null ? (Map<String, Object>) data.get("payment") : null;

            logger.debug("PaymentServiceImpl.createOrderPayment() $payment:: {}", payment);

            PaymentDto paymentDto = new PaymentDto();

            if (payment != null) {
                paymentDto.setId(Long.valueOf(payment.get("id").toString()));
                paymentDto.setOrderId(Long.valueOf(payment.get("order_id").toString()));
                paymentDto.setOrderNumber((String) payment.get("order_number"));
                paymentDto.setTotalPrice((Double) payment.get("total_price"));
                paymentDto.setAdditionalFee((Double) payment.get("additional_fee"));
                paymentDto.setTotalPayment((Double) payment.get("total_payment"));
                paymentDto.setSnapToken((String) payment.get("snap_token"));
                paymentDto.setPaymentStatus((String) payment.get("payment_status"));
                paymentDto.setPaymentExpireAt(LocalDateTime.parse((String) payment.get("payment_expire_at")));
                paymentDto.setIsExpired((Boolean) payment.get("is_expired"));
                paymentDto.setCreatedAt(LocalDateTime.parse((String) payment.get("created_at")));
                paymentDto.setUpdatedAt(LocalDateTime.parse((String) payment.get("updated_at")));
            }

            return paymentDto;
        }  catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Override
    public PaymentDto getPaymentDataByOrderId(Long orderId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> paymentFeignClientGetPaymentDataByOrderIdResponseEntity = paymentFeignClient.getPaymentDataByOrderId(correlationId, orderId);
            ResponseDto responseBody = paymentFeignClientGetPaymentDataByOrderIdResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> payment = data != null ? (Map<String, Object>) data.get("payment") : null;

            logger.debug("PaymentServiceImpl.getPaymentDataByOrderId() $payment:: {}", payment);

            PaymentDto paymentDto = new PaymentDto();

            if (payment != null) {
                paymentDto.setId(Long.valueOf(payment.get("id").toString()));
                paymentDto.setOrderId(Long.valueOf(payment.get("order_id").toString()));
                paymentDto.setOrderNumber((String) payment.get("order_number"));
                paymentDto.setTotalPrice((Double) payment.get("total_price"));
                paymentDto.setAdditionalFee((Double) payment.get("additional_fee"));
                paymentDto.setTotalPayment((Double) payment.get("total_payment"));
                paymentDto.setSnapToken((String) payment.get("snap_token"));
                paymentDto.setPaymentStatus((String) payment.get("payment_status"));
                paymentDto.setPaymentExpireAt(LocalDateTime.parse((String) payment.get("payment_expire_at")));
                paymentDto.setIsExpired((Boolean) payment.get("is_expired"));
                paymentDto.setCreatedAt(LocalDateTime.parse((String) payment.get("created_at")));
                paymentDto.setUpdatedAt(LocalDateTime.parse((String) payment.get("updated_at")));
            }

            return paymentDto;
        }  catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
