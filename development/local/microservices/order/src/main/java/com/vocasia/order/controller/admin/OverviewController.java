package com.vocasia.order.controller.admin;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.service.IOrderService;
import feign.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class OverviewController {

    private final Logger logger = LoggerFactory.getLogger(OverviewController.class);

    private final IOrderService orderService;

    public OverviewController(IOrderService iOrderService) {
        this.orderService = iOrderService;
    }

    @GetMapping("/overview")
    public ResponseEntity<ResponseDto> getOverview() {
        logger.info("OverviewController.getOverview called");

        Map<String, Object> response = new HashMap<>();
        response.put("total_orders_count", orderService.count());
        response.put("total_pending_orders_count", orderService.countByPaymentStatus("pending"));
        response.put("total_success_orders_count", orderService.countByPaymentStatus("success"));

        response.put("total_orders", orderService.sumTotalPrice());
        response.put("total_pending_orders", orderService.sumTotalPriceByStatus("pending"));
        response.put("total_success_orders", orderService.sumTotalPriceByStatus("success"));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data ringkasan order", response, null));
    }

}
