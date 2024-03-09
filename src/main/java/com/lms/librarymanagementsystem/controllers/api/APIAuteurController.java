package com.lms.librarymanagementsystem.controllers.api;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIAuteurController {

    @Autowired
    private AuteurService auteurService;

    // Récupérer la liste de tous les auteurs
    @GetMapping("/auteurs")
    public List<Auteur> getAllAuteurs() {
        return auteurService.getAllAuteurs();
    }

    // Récupérer un auteur par son ID
    @GetMapping("/auteurs/{id}")
    public Auteur getAuteurById(@PathVariable("id") int id) {
        return auteurService.getAuteurById(id);
    }

    // Ajouter un nouvel auteur
    @PostMapping("/auteurs")
    public Auteur addAuteur(@RequestBody Auteur auteur) {
        return auteurService.addAuteur(auteur);
    }

    // Mettre à jour un auteur existant
    @PutMapping("/auteurs/{id}")
    public Auteur updateAuteur(@PathVariable("id") int id, @RequestBody Auteur auteur) {
        return auteurService.updateAuteur(id, auteur);
    }

    // Supprimer un auteur par son ID
    @DeleteMapping("/auteurs/{id}")
    public void deleteAuteur(@PathVariable("id") int id) {
        //
    }
}
