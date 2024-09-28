package com.vocasia.authentication.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "keycloack")
@Getter
@Setter
public class KeycloackConfig {

    private String serverUrl;
    private String clientId;
    private String clientSecret;
    private String realm;
    private String authClientId;

}
