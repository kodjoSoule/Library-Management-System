package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.repository.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuteurService {
    @Autowired
    private AuteurRepository auteurRepository;
    // Ajoutez les méthodes pour les opérations sur les auteurs

    public List<Auteur> getAllAuteurs() {

        return auteurRepository.findAll();
    }
    public Auteur saveAuteur(Auteur auteur) {
        return auteurRepository.save(
                auteur
        );
    }
    public Auteur getAuteurById(int id) {
        return auteurRepository.findById(id);
    }

    public Auteur addAuteur(Auteur auteur) {
        return auteurRepository.save(auteur);
    }
    //update author by id and author
    public Auteur updateAuteur(int id, Auteur auteur) {
        if(auteurRepository.findById(id) != null) {
            return auteurRepository.save(auteur);
        }
        return null;
    }

    public void deleteAuteur(Long id) {
        auteurRepository.deleteById(id);
    }

    public Page<Auteur> findPaginated(Pageable Pageable) {
        return auteurRepository.findAll(Pageable);
    }

}
