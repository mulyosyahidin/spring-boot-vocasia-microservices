package com.vocasia.authentication.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default")
@Getter
@Setter
public class DefaultAdminConfig {

    private AdminUser admin;

    @Getter
    @Setter
    public static class AdminUser {

        private String username;
        private String password;
        private String email;

    }

}
