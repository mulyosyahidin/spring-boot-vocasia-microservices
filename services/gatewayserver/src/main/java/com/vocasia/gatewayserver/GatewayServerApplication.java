package com.vocasia.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p.path("/authentication/**")
                        .filters(f -> f.rewritePath("/authentication/(?<segment>.*)", "/api/${segment}"))
                        .uri("lb://AUTHENTICATION"))
                .route(p -> p.path("/instructor/**")
                        .filters(f -> f.rewritePath("/instructor/(?<segment>.*)", "/api/${segment}"))
                        .uri("lb://INSTRUCTOR"))
                .route(p -> p.path("/course/**")
                        .filters(f -> f.rewritePath("/course/(?<segment>.*)", "/api/${segment}"))
                        .uri("lb://COURSE"))
                .route(p -> p.path("/order/**")
                        .filters(f -> f.rewritePath("/order/(?<segment>.*)", "/api/${segment}"))
                        .uri("lb://ORDER"))
                .route(p -> p.path("/payment/**")
                        .filters(f -> f.rewritePath("/payment/(?<segment>.*)", "/api/${segment}"))
                        .uri("lb://PAYMENT"))
                .route(p -> p.path("/enrollment/**")
                        .filters(f -> f.rewritePath("/enrollment/(?<segment>.*)", "/api/${segment}"))
                        .uri("lb://ENROLLMENT"))
                .route(p -> p.path("/finance/**")
                        .filters(f -> f.rewritePath("/finance/(?<segment>.*)", "/api/${segment}"))
                        .uri("lb://FINANCE"))
                .build();
    }

}
