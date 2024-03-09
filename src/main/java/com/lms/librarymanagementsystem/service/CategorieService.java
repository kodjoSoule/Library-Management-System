package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Categorie;
import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.repository.CategorieRepository;
import com.lms.librarymanagementsystem.repository.LivreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }
    public Categorie getCategorieById(Long id) {
        Categorie searchCategorie = categorieRepository.findById(id).orElse(null);
        return searchCategorie;
    }
    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }


    public List<Categorie> getCategories() {
        return categorieRepository.findAll();
    }
}
