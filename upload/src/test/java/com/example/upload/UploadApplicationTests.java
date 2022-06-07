package com.example.upload;

import com.example.upload.controller.UploadController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest
class UploadApplicationTests {
	@Autowired
	UploadController controller;

	@Test
	void contextLoads() {

	}

}
