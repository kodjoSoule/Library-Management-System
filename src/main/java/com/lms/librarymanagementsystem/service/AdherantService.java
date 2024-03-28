package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Adherent;
import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.repository.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdherantService {
    @Autowired
    AdherantRepository adherentRepository;
    public Adherent findByUsername(String username){
        return adherentRepository.findByUsername(username);
    }
    public void saveAdherent(Adherent adherent){
        adherentRepository.save(adherent);
    }
    public void delete(Adherent adherent){
        adherentRepository.delete(adherent);
    }
    public void deleteById(Long id){
        adherentRepository.deleteById(id);
    }
    public void deleteAll(){
        adherentRepository.deleteAll();
    }


    public Adherent getAdherantById(int i) {
        return adherentRepository.findById((long) i).get();
    }

    public List<Adherent> getAllAdherants() {
        return adherentRepository.findAll();
    }

    public void saveUtilisateur(Utilisateur utilisateur) {
    }
}
