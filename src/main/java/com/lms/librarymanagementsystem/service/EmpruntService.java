package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Adherent;
import com.lms.librarymanagementsystem.model.Emprunt;
import com.lms.librarymanagementsystem.repository.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Emprunt> getEmpruntsByAdherent(Adherent adherent1) {
        return empruntRepository.findByAdherent(adherent1);
    }
}
