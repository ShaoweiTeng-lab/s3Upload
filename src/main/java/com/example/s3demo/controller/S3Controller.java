package com.example.s3demo.controller;

import com.example.s3demo.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {
    @Autowired
    private S3Service s3Service;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("bucketName") String bucketName,
            @RequestParam("file") MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String response = s3Service.uploadFile(bucketName, fileName, file);
        return ResponseEntity.ok(response);
    }
}
