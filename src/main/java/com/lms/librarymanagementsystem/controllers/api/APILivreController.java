package com.lms.librarymanagementsystem.controllers.api;

import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.service.api.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/livres")
public class APILivreController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(APILivreController.class);

    @Autowired
    private LivreService livreService;

    // afficher tous les livres
    @GetMapping("")
    public ResponseEntity<List<Livre>> getAllLivres() {
        List<Livre> livres = livreService.getAllLivres();
        return new ResponseEntity<>(livres, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long id) {
        return livreService.getLivreById(id)
                .map(livre -> new ResponseEntity<>(livre, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Livre> saveLivre(@RequestBody Livre livre) {
        log.info("Livre: {}", livre);
        Livre savedLivre = livreService.saveLivre(livre);
        return new ResponseEntity<Livre>(savedLivre, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}