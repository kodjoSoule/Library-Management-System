package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
    Optional<ImageData> findByName(String fileName);
}

