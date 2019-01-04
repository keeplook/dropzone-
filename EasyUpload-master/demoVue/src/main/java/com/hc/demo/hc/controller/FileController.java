package com.hc.demo.hc.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hc.demo.hc.entity.File;
import com.hc.demo.hc.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    IFileService fileUploadService;

    // Download a file
    @RequestMapping(
            value = "/download",
            method = RequestMethod.GET
    )
    public ResponseEntity downloadFile(@RequestParam("filename") String filename) {
        Wrapper<File> wrapper = new QueryWrapper<File>(new File()).eq("filename", filename);
        File fileUpload = fileUploadService.getOne(wrapper);

        // No file found based on the supplied filename
        if (fileUpload == null) {
            return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND);
        }

        // Generate the http headers with the file properties
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; filename=" + fileUpload.getFilename());

        // Split the mimeType into primary and sub types
        String primaryType, subType;
        try {
            primaryType = fileUpload.getMimetype().split("/")[0];
            subType = fileUpload.getMimetype().split("/")[1];
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        headers.setContentType(new MediaType(primaryType, subType));

        return new ResponseEntity<>(fileUpload.getFile(), headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/upload",
            method = RequestMethod.POST
    )
    public ResponseEntity uploadFile(MultipartHttpServletRequest request) {

        try {
            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                String mimeType = file.getContentType();
                String filename = file.getOriginalFilename();
                byte[] bytes = file.getBytes();

                File newFile = new File();
                newFile.setFile(bytes);
                newFile.setFilename(filename);
                newFile.setMimetype(mimeType);
                fileUploadService.save(newFile);

            }
        } catch (Exception e) {

            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
