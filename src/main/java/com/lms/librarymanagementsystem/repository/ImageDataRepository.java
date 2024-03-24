package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    ImageData findByName(String name);
}