package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByUsername(String username);
    Page<Utilisateur> findAll(Pageable pageable);

}
