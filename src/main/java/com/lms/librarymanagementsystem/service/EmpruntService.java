package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Adherent;
import com.lms.librarymanagementsystem.model.Emprunt;
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

    public List<Emprunt> getEmpruntsByAdherent(Adherent adherent1) {
        return empruntRepository.findByAdherent(adherent1);
    }

    public List<Emprunt> getRetoursByAdherent(Adherent adherent1) {
        return empruntRepository.findByAdherentAndRetourne(adherent1, true);
    }

    public Page<Emprunt> findPaginated(Pageable pageable) {
        return empruntRepository.findAll(pageable);
    }
}
