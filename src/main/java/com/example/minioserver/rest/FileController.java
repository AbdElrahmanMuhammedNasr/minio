package com.example.minioserver.rest;

import com.example.minioserver.file.MinioFile;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class FileController {
    MinioFile minioFile;

    @PostMapping(value="/upload/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        minioFile.saveFile(file);
    }

    @GetMapping(value="/get/file")
    public String getFileUrl() throws Exception {
        return minioFile.getFile();
    }
}
