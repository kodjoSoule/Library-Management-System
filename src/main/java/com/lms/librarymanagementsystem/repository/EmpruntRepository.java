package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Emprunt;
import com.lms.librarymanagementsystem.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    List<Emprunt> findByUtilisateur(Utilisateur user1);
    public Optional<Emprunt> findById(Long id);
    void deleteById(Long id);


    List<Emprunt> findByUtilisateurAndRetourne(Utilisateur utilisateur1, boolean b);
}
