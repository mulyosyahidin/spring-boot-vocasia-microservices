package com.vocasia.payment.controller.instructor;

import com.vocasia.payment.config.PaymentConfigProperties;
import com.vocasia.payment.dto.ResponseDto;
import com.vocasia.payment.entity.Payment;
import com.vocasia.payment.mapper.PaymentMapper;
import com.vocasia.payment.service.IMidtransPaymentService;
import com.vocasia.payment.service.IPaymentService;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor")
public class InstructorPaymentController {

    private final Logger logger = LoggerFactory.getLogger(InstructorPaymentController.class);

    private final IPaymentService paymentService;

    public InstructorPaymentController(IPaymentService iPaymentService) {
        this.paymentService = iPaymentService;
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
