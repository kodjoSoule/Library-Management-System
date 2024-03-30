package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByUsername(String username);
    Page<Utilisateur> findAll(Pageable pageable);

    Object countUtilisateursByRole(String admin);

    List<Utilisateur> findUtilisateursByRole(String admin);
}
