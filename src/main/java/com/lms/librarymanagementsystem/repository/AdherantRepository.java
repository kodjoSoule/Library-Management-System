package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Adherent;
import com.lms.librarymanagementsystem.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherantRepository extends JpaRepository<Adherent, Long> {
    Adherent findByUsername(String username);

}
