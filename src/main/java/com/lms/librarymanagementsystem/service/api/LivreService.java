package com.lms.librarymanagementsystem.service.api;

import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.repository.LivreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;
    @Transactional
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }
    @Transactional
    public List<Livre> getLivreByTitre(String titre) {
        return livreRepository.findByTitre(titre);
    }

    @Transactional
    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }

    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }

    public void save(Livre livre) {
        livreRepository.save(livre);
    }

}
