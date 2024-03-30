package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Penalite;
import com.lms.librarymanagementsystem.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenaliteRepository extends JpaRepository<Penalite, Long> {
    List<Penalite> findByUtilisateur(Utilisateur user);
}
