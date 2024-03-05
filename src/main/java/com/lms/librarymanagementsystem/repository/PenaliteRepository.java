package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Penalite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaliteRepository extends JpaRepository<Penalite, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
