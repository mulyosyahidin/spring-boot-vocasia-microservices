package com.vocasia.payment.controller.student;

import com.vocasia.payment.config.PaymentConfigProperties;
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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
@Validated
public class StudentPaymentController {

    private final Logger logger = LoggerFactory.getLogger(StudentPaymentController.class);

    private final PaymentConfigProperties paymentConfigProperties;
    private final IPaymentService paymentService;
    private final IMidtransPaymentService midtransPaymentService;

    public StudentPaymentController(PaymentConfigProperties paymentConfigProperties, IPaymentService iPaymentService, IMidtransPaymentService iMidtransPaymentService) {
        this.paymentConfigProperties = paymentConfigProperties;
        this.paymentService = iPaymentService;
        this.midtransPaymentService = iMidtransPaymentService;
    }

    @PostMapping("/create-order-payment")
    public ResponseEntity<ResponseDto> createOrderPayment(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @Valid @RequestBody CreateOrderPaymentRequest createOrderPaymentRequest) {
        logger.info("StudentPaymentController.createOrderPayment called");

        createOrderPaymentRequest.setAdditionalFee(paymentConfigProperties.getAdditionalFee());

        Map<String, Object> response = new HashMap<>();

//        Payment payment = new Payment();
//        payment.setId(1L);
//        payment.setOrderId(1L);
//        payment.setOrderNumber("202410303ZKEF");
//        payment.setTotalPrice(100000.0);
//        payment.setAdditionalFee(5000.0);
//        payment.setTotalPayment(105000.0);
//        payment.setSnapToken("snapToken");
//        payment.setPaymentStatus("PENDING");
//        payment.setPaymentExpireAt(LocalDateTime.now());
//        payment.setCreatedAt(LocalDateTime.now());
//        payment.setUpdatedAt(LocalDateTime.now());
//
//        response.put("payment", PaymentMapper.mapToDto(payment));

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

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<ResponseDto> getPaymentDataByOrderId(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                               @PathVariable Long orderId) {
        logger.info("StudentPaymentController.getPaymentDataByOrderId called");

        Payment payment = paymentService.findByOrderId(orderId);

        Map<String, Object> response = new HashMap<>();
        response.put("payment", PaymentMapper.mapToDto(payment));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data pembayaran", response, null));
    }

}
