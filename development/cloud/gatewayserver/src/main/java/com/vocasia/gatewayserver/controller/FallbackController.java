package com.vocasia.gatewayserver.controller;

import com.vocasia.gatewayserver.dto.FallbackDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    private final Logger logger = LoggerFactory.getLogger(FallbackController.class);

    @RequestMapping("/authentication")
    public Mono<FallbackDto> authenticationFallback() {
        return Mono.just(new FallbackDto(false, "Authentication service is not available"));
    }

    @RequestMapping("/instructor")
    public Mono<FallbackDto> instructorFallback() {
        return Mono.just(new FallbackDto(false, "Instructor service is not available"));
    }

    @RequestMapping("/course")
    public Mono<FallbackDto> courseFallback() {
        return Mono.just(new FallbackDto(false, "Course service is not available"));
    }

    @RequestMapping("/order")
    public Mono<FallbackDto> orderFallback() {
        return Mono.just(new FallbackDto(false, "Order service is not available"));
    }

    @RequestMapping("/payment")
    public Mono<FallbackDto> paymentFallback() {
        return Mono.just(new FallbackDto(false, "Payment service is not available"));
    }

    @RequestMapping("/enrollment")
    public Mono<FallbackDto> enrollmentFallback() {
        return Mono.just(new FallbackDto(false, "Enrollment service is not available"));
    }

    @RequestMapping("/finance")
    public Mono<FallbackDto> financeFallback() {
        return Mono.just(new FallbackDto(false, "Finance service is not available"));
    }

    @RequestMapping("/qna")
    public Mono<FallbackDto> qnaFallback() {
        return Mono.just(new FallbackDto(false, "QnA service is not available"));
    }

    @RequestMapping("/catalog")
    public Mono<FallbackDto> catalogFallback() {
        return Mono.just(new FallbackDto(false, "Catalog service is not available"));
    }

}
