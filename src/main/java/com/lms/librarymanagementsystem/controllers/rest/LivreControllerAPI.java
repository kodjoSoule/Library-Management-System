package com.lms.librarymanagementsystem.controllers.rest;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.model.RequestLivre;
import com.lms.librarymanagementsystem.service.AuteurService;
import com.lms.librarymanagementsystem.service.LivreService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivreControllerAPI {
    @Autowired
    private LivreService livreService;
    @Autowired
    private AuteurService auteurService;

    // Endpoint pour récupérer tous les livres
    @GetMapping("/api/livres")
    public ResponseEntity<List<Livre>> getAllLivres() {
        List<Livre> livres = livreService.getAllLivres();
        return ResponseEntity.ok(livres);
    }
    // Endpoint pour ajouter un nouveau livre
    //test message
    // Endpoint pour ajouter un nouveau livre
    @PostMapping("/api/livre")
    public ResponseEntity<String> saveLivre(@RequestBody RequestLivre requestLivre) {
        Livre nouveauLivre = new Livre();
        nouveauLivre.setTitre(requestLivre.getTitre());
        //recherche si author existe
        Auteur auteur = auteurService.getAuteurById(requestLivre.getAuteurId());
        if (auteur == null) {
            return new ResponseEntity<>("Auteur non trouvé", HttpStatus.NOT_FOUND);
        }
        nouveauLivre.setAuteur(auteur);
        nouveauLivre.setDescription(requestLivre.getDescription());
        nouveauLivre.setNbPages(requestLivre.getNbPages());

        nouveauLivre.setDatePublication(requestLivre.getDatePublication());
        nouveauLivre.setEditeur(requestLivre.getEditeur());
        nouveauLivre.setLangue(requestLivre.getLangue());
        // Vous pouvez définir d'autres propriétés du livre en fonction de votre modèle
        Livre savedLivre = livreService.saveLivre(nouveauLivre);
        return new ResponseEntity<>("Livre ajouté avec succès \n" + savedLivre, HttpStatus.CREATED);
    }


    //test message
    @PutMapping("/api/livre/{id}")
    public ResponseEntity<String> updateLivre() {
        return  new ResponseEntity<>("Livre modifié avec succès", HttpStatus.OK);
    }



    // Endpoint pour récupérer un livre par son ID
    @GetMapping("/api/livres/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable("id") Long id) {
        Livre livre = livreService.getLivreById(id);
        if (livre == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livre);
    }
    // Endpoint pour mettre à jour un livre existant
    @PutMapping("/api/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable("id") Long id, @RequestBody Livre livre) {
        Livre existingLivre = livreService.getLivreById(id);
        if (existingLivre == null) {
            return ResponseEntity.notFound().build();
        }
        livre.setId(id);
        Livre updatedLivre = livreService.saveLivre(livre);
        return ResponseEntity.ok(updatedLivre);
    }
    // Endpoint pour supprimer un livre
    @DeleteMapping("/api/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable("id") Long id) {
        Livre existingLivre = livreService.getLivreById(id);
        if (existingLivre == null) {
            return ResponseEntity.notFound().build();
        }
        livreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}