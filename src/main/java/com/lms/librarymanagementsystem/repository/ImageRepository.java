package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Image;
import com.lms.librarymanagementsystem.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}

