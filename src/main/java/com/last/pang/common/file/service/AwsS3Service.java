package com.last.pang.common.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.last.pang.common.file.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public List<Image> uploadFileList(List<MultipartFile> multipartFile, String dirName) {
        List<Image> imageList = new ArrayList<>();
        multipartFile.forEach(file -> {
            Image image = upload(file, dirName);
            imageList.add(image);
        });
        return imageList;
    }

    public Image upload(MultipartFile multipartFile, String dirName) {
        File file = null;
        try {
            file = convertMultipartFileToFile(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return upload(file, dirName);
    }

    private Image upload(File file, String dirName) {
        String key = randomFileName(file, dirName);
        String path = putS3(file, key);
        removeFile(file);

        return Image
                .builder()
                .fileKey(key)
                .filePath(path)
                .build();
    }

    private String randomFileName(File file, String dirName) {
        return dirName + "/" + UUID.randomUUID() + file.getName();
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return getS3(bucket, fileName);
    }

    private String getS3(String bucket, String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private void removeFile(File file) {
        file.delete();
    }

    public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        }
        return file;
    }

    public void remove(Image image) {
        if (!amazonS3.doesObjectExist(bucket, image.getFileKey())) {
            throw new AmazonS3Exception("Object " + image.getFileKey() + " does not exist!");
        }
        amazonS3.deleteObject(bucket, image.getFileKey());
    }

    public void removeImageList(List<Image> imageList) {
        imageList.forEach(image -> {
            if (!amazonS3.doesObjectExist(bucket, image.getFileKey())) {
                throw new AmazonS3Exception("Object " + image.getFileKey() + " does not exist!");
            }
            amazonS3.deleteObject(bucket, image.getFileKey());
        });
    }
}
