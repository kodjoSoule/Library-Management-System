package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Livre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

    @Query("SELECT livre FROM Livre livre ORDER BY livre.nbExemplairesEmpruntes DESC")
    List<Livre> findTopEmprunteLivres(Pageable pageable);

    Page<Livre> findByTitreContainingIgnoreCase(String s, PageRequest of);
}
