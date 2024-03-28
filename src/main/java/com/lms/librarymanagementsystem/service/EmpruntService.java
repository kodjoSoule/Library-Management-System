package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.model.Emprunt;
import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.repository.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpruntService {
    @Autowired
    EmpruntRepository empruntRepository;
    public void deleteById(Long id){
        empruntRepository.deleteById(id);
    }
    public void deleteAll(){
        empruntRepository.deleteAll();
    }

    public void saveEmprunt(Emprunt emprunt) {
        empruntRepository.save(emprunt);
    }



    public Page<Emprunt> findPaginated(Pageable pageable) {
        return empruntRepository.findAll(pageable);
    }

    public List<Emprunt> getEmpruntsByUtilisateur(Utilisateur utilisateur2) {
        return empruntRepository.findByUtilisateur(utilisateur2);
    }

    public List<Emprunt> getRetoursByUtilisateur(Utilisateur utilisateur1) {
        return empruntRepository.findByUtilisateurAndRetourne(utilisateur1, true);
    }

    public Emprunt getEmpruntsById(Long emprundId) {
        return empruntRepository.findById(emprundId).orElse(null);
    }
}
