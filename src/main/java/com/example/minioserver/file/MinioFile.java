package com.example.minioserver.file;

import com.example.minioserver.config.MinioData;
import io.minio.*;
import io.minio.http.Method;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class MinioFile {
    MinioClient  minioClient;
    MinioData minioData;
    public void saveFile(MultipartFile file) throws Exception {

        List<String> userIds = new LinkedList<>();
        userIds.add("123698");
        userIds.add("123699");
        userIds.add("123700");
        userIds.add("123701");
        Collections.shuffle(userIds);
        String userId = userIds.stream().findFirst().get();

        List<String> types = new LinkedList<>();
        types.add("profile");
        types.add("medical");
        types.add("personal");
        types.add("work");
        Collections.shuffle(types);
        String type = types.stream().findFirst().get();


        List<String> services = new LinkedList<>();
        services.add("users");
        services.add("order");
        services.add("bank");
        Collections.shuffle(services);
        String service = services.stream().findFirst().get();




        InputStream fileInputStream = file.getInputStream();
        PutObjectArgs user1 = PutObjectArgs
                .builder()
                .bucket("development")
                .object(service+"/"+type+"/"+userId+"/"+file.getOriginalFilename())
                .stream(fileInputStream, file.getSize(), 5*1024*1024).build();
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(user1);

    }

    public String getFile( ) throws Exception {
        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET) // HTTP method for the URL
                        .bucket("development") // Name of the bucket
                        .object("bank/medical/123701/Queries.xlsx") // Name of the object
                        .expiry(10) // Expiry time for the URL
                        .build());
        return url;
    }
}
