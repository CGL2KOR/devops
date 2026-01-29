package com.pavan.s3bucket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pavan.s3bucket.service.TodoServiceImpl;

@RestController
public class StorageController {

	@Autowired
	private TodoServiceImpl service;
	
	@PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        
		return new ResponseEntity<String>(service.upload(file), HttpStatus.OK);
    }
}
