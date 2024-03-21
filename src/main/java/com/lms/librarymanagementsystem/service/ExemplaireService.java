package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Exemplaire;
import com.lms.librarymanagementsystem.model.Livre;

import com.lms.librarymanagementsystem.repository.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExemplaireService {
    @Autowired
    private ExemplaireRepository exemplaireRepository;

    public List<Exemplaire> getAllExemplaires() {
        return exemplaireRepository.findAll();
    }

    public List<Exemplaire> getExemplairesByLivre(Livre livre) {
        return exemplaireRepository.findByLivre(livre);
    }

    public void save(Exemplaire exemplaire) {
        exemplaireRepository.save(exemplaire);
    }

    public void supprimerExemplaire(Long id) {
        exemplaireRepository.deleteById(id);
    }
}
