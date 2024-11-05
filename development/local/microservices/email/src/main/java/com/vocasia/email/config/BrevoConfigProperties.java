package com.vocasia.email.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "brevo")
@Getter
@Setter
public class BrevoConfigProperties {

    private String apiKey;
    private String senderName;
    private String senderEmail;

}
