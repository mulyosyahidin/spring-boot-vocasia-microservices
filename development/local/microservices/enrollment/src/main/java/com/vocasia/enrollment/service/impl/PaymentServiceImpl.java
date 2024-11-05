package com.vocasia.enrollment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.enrollment.dto.ResponseDto;
import com.vocasia.enrollment.dto.client.payment.PaymentDto;
import com.vocasia.enrollment.exception.CustomFeignException;
import com.vocasia.enrollment.service.IPaymentService;
import com.vocasia.enrollment.service.client.PaymentFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PaymentFeignClient paymentFeignClient;

    @Cacheable(value = "order_payment", key = "#orderId")
    @Override
    public PaymentDto findPaymentByOrderId(Long orderId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> paymentFeignClientFindByIdResponseEntity = paymentFeignClient.findById(correlationId, orderId);
            ResponseDto responseBody = paymentFeignClientFindByIdResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> payment = data != null ? (Map<String, Object>) data.get("payment") : null;

            logger.debug("PaymentServiceImpl.findPaymentByOrderId() $payment:: {}", payment);

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
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
