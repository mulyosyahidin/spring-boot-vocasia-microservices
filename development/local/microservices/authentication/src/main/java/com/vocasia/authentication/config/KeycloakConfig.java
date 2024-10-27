package com.vocasia.authentication.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "keycloak")
@Getter
@Setter
public class KeycloakConfig {

    private String serverUrl;
    private String clientId;
    private String clientSecret;
    private String realm;
    private String authClientId;

}
