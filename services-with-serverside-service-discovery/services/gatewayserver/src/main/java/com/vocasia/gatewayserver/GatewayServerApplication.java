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
                        .uri("http://authentication:14120"))
                .route(p -> p.path("/instructor/**")
                        .filters(f -> f.rewritePath("/instructor/(?<segment>.*)", "/api/${segment}"))
                        .uri("http://instructor:14121"))
                .route(p -> p.path("/course/**")
                        .filters(f -> f.rewritePath("/course/(?<segment>.*)", "/api/${segment}"))
                        .uri("http://course:14122"))
                .route(p -> p.path("/order/**")
                        .filters(f -> f.rewritePath("/order/(?<segment>.*)", "/api/${segment}"))
                        .uri("http://order:14124"))
                .route(p -> p.path("/payment/**")
                        .filters(f -> f.rewritePath("/payment/(?<segment>.*)", "/api/${segment}"))
                        .uri("http://payment:14125"))
                .route(p -> p.path("/enrollment/**")
                        .filters(f -> f.rewritePath("/enrollment/(?<segment>.*)", "/api/${segment}"))
                        .uri("http://enrollment:14126"))
                .route(p -> p.path("/finance/**")
                        .filters(f -> f.rewritePath("/finance/(?<segment>.*)", "/api/${segment}"))
                        .uri("http://finance:14127"))
                .route(p -> p.path("/qna/**")
                        .filters(f -> f.rewritePath("/qna/(?<segment>.*)", "/api/${segment}"))
                        .uri("http://qna:14128"))
                .build();
    }

}
