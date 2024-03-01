package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

    Livre findByTitre(String titre);
    Livre findByAuteur(String auteur);
    Livre findByIsbn(String isbn);

}
