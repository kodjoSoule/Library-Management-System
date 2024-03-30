package com.lms.librarymanagementsystem.controllers.rest;

import com.lms.librarymanagementsystem.model.Emprunt;
import com.lms.librarymanagementsystem.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntControllerAPI {

    private final EmpruntService empruntService;

    @Autowired
    public EmpruntControllerAPI(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    @GetMapping
    public ResponseEntity<List<Emprunt>> getAllEmprunts() {
        return ResponseEntity.ok(empruntService.getAllEmprunts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable Long id) {
        return ResponseEntity.ok(empruntService.getEmpruntById(id));
    }

    @PostMapping
    public ResponseEntity<Emprunt> createEmprunt(@RequestBody Emprunt emprunt) {
        return ResponseEntity.ok(empruntService.createEmprunt(emprunt));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprunt> updateEmprunt(@PathVariable Long id, @RequestBody Emprunt emprunt) {
        return ResponseEntity.ok(empruntService.updateEmprunt(id, emprunt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable Long id) {
        empruntService.deleteEmprunt(id);
        return ResponseEntity.ok().build();
    }
}