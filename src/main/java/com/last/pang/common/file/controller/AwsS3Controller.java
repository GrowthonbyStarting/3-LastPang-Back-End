package com.last.pang.common.file.controller;

import com.last.pang.common.file.entity.Image;
import com.last.pang.common.file.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/s3")
@RequiredArgsConstructor
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    @PostMapping("/resource")
    public Image upload(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        return awsS3Service.upload(multipartFile,"upload");
    }

    @DeleteMapping("/resource")
    public void remove(Image image) {
        awsS3Service.remove(image);
    }
}
