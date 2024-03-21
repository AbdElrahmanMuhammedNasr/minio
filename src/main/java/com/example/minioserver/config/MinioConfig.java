package com.example.minioserver.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MinioConfig {
    MinioData  minioData;

    @Bean
    public MinioClient minioClient() throws Exception {
        MinioClient build = MinioClient.builder()
                .endpoint(minioData.getEndpoint())
                .credentials(minioData.getAccessKey(), minioData.getSecretKey())
                .build();

        for (String bucket : minioData.getBucket()) {
            BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucket).build();
            boolean b = build.bucketExists(bucketExistsArgs);
            if(!b){
                MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucket).build();
                build.makeBucket(makeBucketArgs);
            }
            System.out.println(bucket +" " + b);
        }
        return build;

    }

}
