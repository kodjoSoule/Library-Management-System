package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

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
        livre.setNbExemplaires(livre.getNbExemplaires() + nombreExemplaires);
    }

    public void supprimerLivre(Long id) {
        Livre livre = getLivreById(id);
        deleteLivre(id);
    }

    public void ajouterLivre(Livre livre) {
        addExemplaireParNombre(livre, 1);
        saveLivre(livre);
    }

    public boolean existsById(long livreId) {
        return livreRepository.existsById(livreId);
    }

    public Object countLivres() {
        return livreRepository.count();
    }

    public List<Livre> getTopEmprunteLivres() {
        Pageable pageable = PageRequest.of(0, 3); // Nombre de livres à afficher
        return livreRepository.findTopEmprunteLivres(pageable);
    }

    public Page<Livre> findPaginatedAndFiltered(PageRequest of, String s) {
        return livreRepository.findByTitreContainingIgnoreCase(s, of);
    }
}
