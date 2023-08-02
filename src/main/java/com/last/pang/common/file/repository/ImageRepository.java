package com.last.pang.common.file.repository;

import com.last.pang.common.file.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
