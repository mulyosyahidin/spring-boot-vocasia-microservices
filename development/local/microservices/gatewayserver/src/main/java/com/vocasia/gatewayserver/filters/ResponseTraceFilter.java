package com.vocasia.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Configuration
public class ResponseTraceFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
            String correlationId = filterUtility.getCorrelationId(requestHeaders);

            if (!(exchange.getResponse().getHeaders().containsKey(FilterUtility.CORRELATION_ID))) {
                exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
                exchange.getResponse().getHeaders().add(FilterUtility.RESPONSE_TIME_HEADER, LocalDateTime.now().toString());
            }
        }));
    }

}