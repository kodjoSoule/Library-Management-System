package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Exemplaire;
import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private ExemplaireService examplaireService;
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Livre getLivreById(Long id) {
        Livre searchlivre = livreRepository.findById(id).orElse(null);
        return searchlivre;
    }


    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }

    public Livre saveLivre(Livre livre) {
        livreRepository.save(livre);
        return livre;
    }

    //
    public Page<Livre> findPaginated(Pageable Pageable) {
        //Pageable pageable = PageRequest.of(pageNo, pageSize);
        return livreRepository.findAll(Pageable);
    }

    public void delete(Long id) {
        livreRepository.deleteById(id);
    }
    public void addExemplaireParNombre(Livre livre , int nombreExemplaires) {
        for (int i = 0; i < nombreExemplaires; i++) {
            Exemplaire exemplaire = new Exemplaire();
            exemplaire.setLivre(livre);
            examplaireService.save(exemplaire);
            livre.addExemplaire(exemplaire);
        }
    }
}
