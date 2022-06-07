package com.example.upload.controller;

import com.example.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
public class UploadController {

    @Autowired
    UploadService service;

    @PostMapping("/upload")
    public void handleUpload(HttpServletRequest request) throws Exception {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(
                new File(System.getProperty("java.io.tmpdir")));
        factory.setSizeThreshold(
                DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD);
        factory.setFileCleaningTracker(null);

        ServletFileUpload upload = new ServletFileUpload(factory);

        List items = upload.parseRequest(request);
        log.info("***************" + items.size());
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            log.info("ci sta qualcosa!");
//            if (!item.isFormField()) {
//                try (
//                        InputStream uploadedStream = item.getInputStream();
//                        OutputStream out = new FileOutputStream("file.mov");) {
//
//                    IOUtils.copy(uploadedStream, out);
//                }
//            }
        }
        log.info( "success!");
    }
}
