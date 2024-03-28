package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Emprunt;
import com.lms.librarymanagementsystem.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByAdherent(Utilisateur user1);
    public Optional<Emprunt> findById(Long id);
    void deleteById(Long id);
    List<Emprunt> findByAdherentAndRetourne(Utilisateur user1, boolean b);
}
