package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.model.Livre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    // Liste des livres par titre
    public List<Livre> findByTitre(String titre);

    //Liste Livre par pagination
    //public Page<Livre> findAll(Pageable pageable);
//    Page<Livre> findPaginated(Pageable pageable);



}
