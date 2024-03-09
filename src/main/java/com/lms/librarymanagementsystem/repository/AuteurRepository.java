package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    Auteur findById(int id);
    Auteur save(Auteur auteur);

}
