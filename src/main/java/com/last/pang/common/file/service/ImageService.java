package com.last.pang.common.file.service;

import com.last.pang.common.file.entity.Image;
import com.last.pang.common.file.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public void saveImage(List<Image> iamgeList) {
        imageRepository.saveAll(iamgeList);
    }

}
