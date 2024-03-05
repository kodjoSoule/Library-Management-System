package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire


}
