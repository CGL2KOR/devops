package com.pavan.s3bucket.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class TodoServiceImpl {

    @Autowired
    private S3Client s3Client;

    private final String bucketName = "typavan";

    public String upload(MultipartFile file) {

        String keyName = file.getOriginalFilename();

        try {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(
                    putObjectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );

            return "File uploaded successfully: " +
                    "https://" + bucketName + ".s3.ap-south-1.amazonaws.com/" + keyName;

        } catch (S3Exception e) {
            e.printStackTrace();
            return "S3 error while uploading file: " + e.awsErrorDetails().errorMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "IO error while reading file: " + e.getMessage();
        }
    }
}
