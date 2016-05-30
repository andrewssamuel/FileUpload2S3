package com.firstwicket;

import org.junit.*;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.*;
import org.springframework.core.io.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.*;
import org.springframework.util.*;
import org.springframework.web.client.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FileUpload2S3Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class FileUpload2S3ApplicationTests {

	private RestTemplate restTemplate =  new RestTemplate();
	private String url = "http://localhost:9000/upload2s3";

	@Test
	public void upload(){

		MultiValueMap<String,Object> file = new LinkedMultiValueMap<String,Object>();
		file.add("image",new FileSystemResource("Image_upload_test.jpg"));
		file.add("Content-Type", "multipart/form-data");
		String response = restTemplate.postForObject(url,file,String.class);
		Assert.assertEquals(response,"Success-FileUpload:Completed");

	}

}
