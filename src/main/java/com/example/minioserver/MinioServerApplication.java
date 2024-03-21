package com.example.minioserver;

import com.example.minioserver.config.MinioData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {MinioData.class})
public class MinioServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinioServerApplication.class, args);
	}

}
