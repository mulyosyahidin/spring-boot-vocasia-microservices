package com.vocasia.finance.packages.aws.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.vocasia.finance.config.AwsConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3ClientConfig {

    private final AwsConfigProperties awsConfigProperties;

    @Autowired
    public S3ClientConfig(AwsConfigProperties awsConfigProperties) {
        this.awsConfigProperties = awsConfigProperties;
    }

    @Bean
    public AmazonS3 s3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(awsConfigProperties.getAccessKeyId(), awsConfigProperties.getSecretAccessKey());

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(awsConfigProperties.getS3().getRegion())
                .build();
    }

}