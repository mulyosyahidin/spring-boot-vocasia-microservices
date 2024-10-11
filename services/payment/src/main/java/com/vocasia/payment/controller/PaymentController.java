package com.vocasia.payment.controller;

import com.vocasia.payment.dto.ResponseDto;
import com.vocasia.payment.entity.Payment;
import com.vocasia.payment.mapper.PaymentMapper;
import com.vocasia.payment.request.CreateOrderPaymentRequest;
import com.vocasia.payment.service.IMidtransPaymentService;
import com.vocasia.payment.service.IPaymentService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class PaymentController {

    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final IPaymentService paymentService;
    private final IMidtransPaymentService midtransPaymentService;

    public PaymentController(IPaymentService iPaymentService, IMidtransPaymentService iMidtransPaymentService) {
        this.paymentService = iPaymentService;
        this.midtransPaymentService = iMidtransPaymentService;
    }

    @PostMapping("/create-order-payment")
    public ResponseEntity<ResponseDto> createOrderPayment(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @Valid @RequestBody CreateOrderPaymentRequest createOrderPaymentRequest) {
        logger.debug("PaymentController.createOrderPayment called");

        Map<String, Object> response = new HashMap<>();

        try {
            String snapToken = midtransPaymentService.requestSnapToken(createOrderPaymentRequest);
            Payment payment = paymentService.save(snapToken, createOrderPaymentRequest);

            response.put("payment", PaymentMapper.mapToDto(payment));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, e.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil membuat pembayaran order", response, null));
    }

    @GetMapping("/payment-data-by-order-id/{orderId}")
    public ResponseEntity<ResponseDto> getPaymentDataByOrderId(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                               @PathVariable Long orderId) {
        logger.debug("PaymentController.getPaymentDataByOrderId called");

        Payment payment = paymentService.findByOrderId(orderId);

        Map<String, Object> response = new HashMap<>();
        response.put("payment", PaymentMapper.mapToDto(payment));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data pembayaran", response, null));
    }

}
