package com.linoer.springtest.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
@RequestMapping("/up")
public class PictureUploadController {
    private static final Resource PICTURE_DIR = new FileSystemResource("pictures");
    private static final Log logger = LogFactory.getLog(PictureUploadController.class);

    @RequestMapping("/upload")
    public String uploadPage(){
        return "uploadPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        logger.info("upload filename:" + filename);
        logger.info("upload url:" + PICTURE_DIR.getURL());
        if(null != filename){
            File tempFile = File.createTempFile("pic", getFileExtension(filename), PICTURE_DIR.getFile());
            try (InputStream in = file.getInputStream();
                 OutputStream out = new FileOutputStream(tempFile)) {
                IOUtils.copy(in, out);
            }
            return "uploadPage";
        }
        logger.info("upload file is null !");
        return "failure";
    }

    private static String getFileExtension(String name){
        return name.substring(name.lastIndexOf("."));
    }
}
