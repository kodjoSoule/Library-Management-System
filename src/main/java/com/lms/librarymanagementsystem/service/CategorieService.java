package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.model.Categorie;
import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.repository.CategorieRepository;
import com.lms.librarymanagementsystem.repository.LivreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired LivreRepository livreRepository;

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

    public void deleteCategorie(Long id) {
        //remove all books from the category
        Categorie categorie = categorieRepository.findById(id).orElse(null);
        if (categorie != null) {
            categorie.getLivres().clear();
            categorieRepository.save(categorie);
        }
        categorieRepository.deleteById(id);
    }

    public List<Categorie> searchCategorie(String nom) {
        return categorieRepository.findByNomContaining(nom);
    }

    public void removeLivreFromCategorie(Long id, Long idLivre) {
        Categorie categorie = categorieRepository.findById(id).orElse(null);
        if (categorie != null) {
            Livre livre = categorie.getLivres().stream().filter(l -> l.getId().equals(idLivre)).findFirst().orElse(null);
            if (livre != null) {
                categorie.getLivres().remove(livre);
                categorieRepository.save(categorie);
            }
        }
    }

    public void addLivreToCategorie(Long id, Long idLivre) {
        Categorie categorie = categorieRepository.findById(id).orElse(null);

        if (categorie != null) {
            Livre livre = categorie.getLivres().stream().filter(l -> l.getId().equals(idLivre)).findFirst().orElse(null);
            if (livre == null) {
                LivreRepository livreRepository = null;
                Livre livre1 = livreRepository.findById(idLivre).orElse(null);
                if (livre1 != null) {
                    categorie.getLivres().add(livre1);
                    categorieRepository.save(categorie);
                }
            }
        }
    }

    public List<Categorie> findByNom(String title) {
        return categorieRepository.findByNomContaining(title);
    }

    public Optional<Categorie> findById(String id) {
        return categorieRepository.findById(Long.valueOf(id));
    }

    public boolean addLivreToCategorie(Categorie categorie, Long idLivre) {
        Livre livre = livreRepository.findById(idLivre).orElse(null);
        if (livre != null) {
            categorie.getLivres().add(livre);
            categorieRepository.save(categorie);
            return true;
        }
        return false;
    }


    public Categorie getcategorieById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    public Page<Categorie> findPaginated(PageRequest of) {
        return categorieRepository.findAll(of);
    }

    public Page<Categorie> findByNomContainingIgnoreCase(String s, PageRequest of) {
        return categorieRepository.findByNomContainingIgnoreCase(s, of);
    }
}
