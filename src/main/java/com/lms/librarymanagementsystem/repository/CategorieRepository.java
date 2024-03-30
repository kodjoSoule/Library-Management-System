package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Categorie;
import com.lms.librarymanagementsystem.model.Livre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    List<Categorie> findByNomContaining(String nom);

    Page<Categorie> findByNomContainingIgnoreCase(String s, PageRequest of);
}
