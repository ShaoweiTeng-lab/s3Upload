package com.example.s3demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

@Service
public class S3Service {
    private final S3Client s3Client;

    public S3Service() {
        this.s3Client = S3Client.builder()
                .region(Region.AP_NORTHEAST_1) // 替換為你的 S3 區域
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public String uploadFile(String bucketName, String fileName, MultipartFile file) {
        try {
            // 使用 InputStream 讀取 MultipartFile 的內容
            InputStream inputStream = file.getInputStream();

            // 構建 PutObjectRequest
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            // 將 InputStream 上傳到 S3
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, file.getSize()));

            return "File uploaded successfully to bucket: " + bucketName + ", key: " + fileName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to S3: " + e.getMessage(), e);
        }
    }
}
