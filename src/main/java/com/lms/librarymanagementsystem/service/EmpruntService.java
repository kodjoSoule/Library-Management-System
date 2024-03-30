package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.model.Emprunt;
import com.lms.librarymanagementsystem.repository.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
@Service
public class EmpruntService {
    @Autowired
    EmpruntRepository empruntRepository;
    @Autowired
    UtilisateurService utilisateurService ;

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

    public Object countEmprunts() {
        return empruntRepository.count();
    }

    public int countEmpruntsEnRetard() {
        //if dateRetourPrevue > LocalDate.now()
        return empruntRepository.countEmpruntsEnRetard();
    }

    public Emprunt getEmpruntById(Long id) {
        return empruntRepository.findById(id).orElse(null);
    }

    public boolean existsById(Long id) {
        return empruntRepository.existsById(id);
    }

    public Page<Emprunt> findEmpruntEnCoursPaginated(Pageable pageable) {
        return empruntRepository.findByRetourne(false, pageable);
    }

    public Page<Emprunt> findEmpruntRetournerPaginated(PageRequest of) {
        return empruntRepository.findByRetourne(true, of);
    }

    public List<Emprunt> getThreeRecentEmprunts() {
        Pageable pageable = PageRequest.of(0, 3);
        return empruntRepository.findThreeRecentEmprunts(pageable);
    }

    public List<Emprunt> getRetardsEmprunts() {
        List<Emprunt> retardsEmprunts = empruntRepository.findRetardsEmprunts(LocalDate.now());
        return retardsEmprunts;
    }
    public Page<Emprunt> findByRetourne(boolean b, PageRequest of) {
        return empruntRepository.findByRetourne(b, of);
    }

    public Emprunt createEmprunt(Emprunt emprunt) {
        return empruntRepository.save(emprunt);
    }

    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    public Emprunt updateEmprunt(Long id, Emprunt emprunt) {
        return empruntRepository.save(emprunt);
    }

    public void deleteEmprunt(Long id) {
        empruntRepository.deleteById(id);
    }
}
