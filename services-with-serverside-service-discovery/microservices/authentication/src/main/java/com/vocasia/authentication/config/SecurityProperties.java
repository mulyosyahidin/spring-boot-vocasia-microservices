package com.vocasia.authentication.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "security")
@Setter
@Getter
@Component
public class SecurityProperties {

    private String salt;

}

