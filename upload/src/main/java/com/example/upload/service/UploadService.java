package com.example.upload.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
@Service
public class UploadService {
    public void uploadPdf(HttpServletRequest request) {
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        factory.setRepository(
//                new File(System.getProperty("java.io.tmpdir")));
//        factory.setSizeThreshold(
//                DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD);
//        factory.setFileCleaningTracker(null);
//
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        List items = null;
//        try {
//            items = upload.parseRequest(request);
//        } catch (FileUploadException e) {
//            throw new RuntimeException(e);
//        }
//
//        Iterator iter = items.iterator();
//        while (iter.hasNext()) {
//            FileItem item = (FileItem) iter.next();
//
//            if (!item.isFormField()) {
//                try (
//                        InputStream uploadedStream = item.getInputStream();
//                        OutputStream out = new FileOutputStream("file.pdf")) {
//                    IOUtils.copy(uploadedStream, out);
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//            if (!isMultipart) {
//                // Inform user about invalid request
//                log.info("************ Not a multipart request.");
//            }

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload();


            // Parse the request
            FileItemIterator iter = upload.getItemIterator(request);

            while (iter.hasNext()) {
                log.info("Sono dentro");

                FileItemStream item = iter.next();
                String name = item.getFieldName();
                InputStream stream = item.openStream();
                if (!item.isFormField()) {
                    String filename = item.getName();
                    log.info("****"+filename);

                    // Process the input stream
                    OutputStream out = new FileOutputStream(filename);
                    IOUtils.copy(stream, out);
                    stream.close();
                    out.close();
                }
            }
        } catch (FileUploadException e) {
            log.info("File upload error");
        } catch (IOException e) {
            log.info("Internal server IO error");
        }

    }
}
