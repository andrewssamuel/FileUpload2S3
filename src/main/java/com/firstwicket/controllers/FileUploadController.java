package com.firstwicket.controllers;

import com.firstwicket.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.io.*;

/**
 * Created by andrewssamuel on 5/28/16.
 */
@RestController
public class FileUploadController {

    @Autowired
    FileUploader fileUploader;

    @RequestMapping(method = RequestMethod.POST, value = "/upload2s3")
    public String upload2S3(@RequestParam("image") MultipartFile mfile) throws IOException {

        String status = fileUploader.upload2S3(mfile);

        return status;

    }
}
