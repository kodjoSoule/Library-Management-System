package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.ImageLivre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageLivre, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}

