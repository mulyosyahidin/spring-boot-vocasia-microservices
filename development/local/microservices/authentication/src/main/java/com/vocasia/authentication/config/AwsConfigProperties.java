package com.vocasia.authentication.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws")
@Getter
@Setter
public class AwsConfigProperties {

    private String accessKeyId;
    private String secretAccessKey;
    private S3Properties s3;

    @Getter
    @Setter
    public static class S3Properties {
        private String region;
        private String bucket;
    }

}
