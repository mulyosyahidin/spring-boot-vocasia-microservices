package com.vocasia.playground.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "keycloack")
@Getter
@Setter
public class KeycloackConfig {

    private String server;

}

