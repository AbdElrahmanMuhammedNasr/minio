package com.example.minioserver.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "minio")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MinioData {
     String service;
     String endpoint;
     String accessKey;
     String secretKey;
     List<String> bucket;

}
