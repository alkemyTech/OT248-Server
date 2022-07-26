package com.alkemy.ong.controller;

import com.alkemy.ong.service.impl.AmazonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage/")
public class AmazonS3Controller {

    private AmazonServiceImpl amazonService;

    @Autowired
    AmazonS3Controller(AmazonServiceImpl amazonService){
        this.amazonService = amazonService;
    }


    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity.ok().body(this.amazonService.uploadFile(file));
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonService.deleteFileFromS3Bucket(fileUrl);
    }

}
