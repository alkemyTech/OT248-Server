package com.alkemy.ong.service;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonService {

    public String uploadFile(MultipartFile multipartFile);

    public String deleteFileFromS3Bucket(String fileUrl);

}
