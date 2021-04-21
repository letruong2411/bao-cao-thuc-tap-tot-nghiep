package com.timtro247.maven1311.repository;

import com.timtro247.maven1311.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Images, Long> {
}
