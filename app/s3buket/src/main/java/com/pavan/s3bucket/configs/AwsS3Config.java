package com.pavan.s3bucket.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsS3Config {

    @Bean
    public AmazonS3 s3bucket() {
        return AmazonS3ClientBuilder.standard()
                .withRegion("ap-south-1")
                .build();  
        // credentials automatically picked from env / profile / IAM role
    }
}
