package com.firstwicket.services;


import org.springframework.web.multipart.*;


/**
 * Created by andrewssamuel on 5/28/16.
 */

public interface FileUploader {

    public String upload2S3(MultipartFile file);

}
