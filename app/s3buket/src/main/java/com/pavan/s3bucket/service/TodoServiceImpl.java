package com.pavan.s3bucket.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;

@Service
public class TodoServiceImpl {

	@Autowired
    private AmazonS3 amazonS3Client;
	
	
	public String upload(MultipartFile file) {
		String bucketName = "typavan";
		String keyName = file.getOriginalFilename();
		try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
        amazonS3Client.putObject(bucketName, keyName, file.getInputStream(), metadata);
         
        return "File uploaded in location: " + "https://"+bucketName+".s3.ap-south-1.amazonaws.com/"+file.getOriginalFilename();
        }catch (Exception e) {
			e.printStackTrace();
		}
        return "File not uploaded: " + keyName;
	}
}
