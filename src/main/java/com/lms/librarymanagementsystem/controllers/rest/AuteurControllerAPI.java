package com.lms.librarymanagementsystem.controllers.rest;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuteurControllerAPI {

    @Autowired
    private AuteurService auteurService;

    // Récupérer la liste de tous les auteurs
    @GetMapping("/api//auteurs")
    public List<Auteur> getAllAuteurs() {
        return auteurService.getAllAuteurs();
    }

    // Récupérer un auteur par son ID
    @GetMapping("/api/auteur/{id}")
    public Auteur getAuteurById(@PathVariable("id") int id) {
        return auteurService.getAuteurById(id);
    }

    // Ajouter un nouvel auteur
    @PostMapping("/api/uteur")
    public Auteur addAuteur(@RequestBody Auteur auteur) {
        return auteurService.addAuteur(auteur);
    }

    // Mettre à jour un auteur existant
    @PutMapping("/api/auteur/{id}")
    public Auteur updateAuteur(@PathVariable("id") int id, @RequestBody Auteur auteur) {
        return auteurService.updateAuteur(id, auteur);
    }


    @DeleteMapping("/api/auteur/{id}")
    public void deleteAuteur(@PathVariable("id") int id) {
        auteurService.deleteAuteur((long) id);
    }
}
