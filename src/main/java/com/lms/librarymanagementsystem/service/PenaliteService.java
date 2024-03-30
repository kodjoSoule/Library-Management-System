package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Penalite;
import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.repository.PenaliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaliteService {
    @Autowired
    private PenaliteRepository penaliteRepository;

    public void savePenalite(Penalite penalite) {
        penaliteRepository.save(penalite);
    }

    public List<Penalite> getPenalitesByUtilisateur(Utilisateur user) {
        return penaliteRepository.findByUtilisateur(user);
    }
}
