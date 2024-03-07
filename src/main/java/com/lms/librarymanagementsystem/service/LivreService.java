package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.repository.LivreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }
    public List<Livre> getLivreByTitre(String titre) {
        return livreRepository.findByTitre(titre);
    }


    public Livre getLivreById(Long id) {
        Livre searchlivre = livreRepository.findById(id).orElse(null);
        return searchlivre;
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

    //
    public Page<Livre> findPaginated(Pageable Pageable) {
        //Pageable pageable = PageRequest.of(pageNo, pageSize);
        return livreRepository.findAll(Pageable);
    }
    //


}
