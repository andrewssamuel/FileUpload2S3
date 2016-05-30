package com.firstwicket.services;

import com.amazonaws.*;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;


/**
 * Created by andrewssamuel on 5/28/16.
 */
@Service
public class FileUploaderImpl implements FileUploader {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${bucket}")
    private String bucketName;

    @Override
    public String upload2S3(MultipartFile file) {

        TransferManager transferManager = new TransferManager(this.amazonS3);
        Upload upload = null;
        try {
            upload = transferManager.upload(bucketName, file.getOriginalFilename(), file.getInputStream(), generateObjectMetaData(file));
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            // Or you can block and wait for the upload to finish
            try {
                upload.waitForCompletion();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Success-FileUpload:" + upload.getState();
        } catch (AmazonClientException amazonClientException) {
            amazonClientException.printStackTrace();
            return "Unable to upload file, upload was aborted.";
        }


    }

    private ObjectMetadata generateObjectMetaData(MultipartFile file) {

        ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.addUserMetadata("UploadedBy", "Andrews");
        objectMetadata.setContentType(file.getContentType());

        return objectMetadata;
    }
}
