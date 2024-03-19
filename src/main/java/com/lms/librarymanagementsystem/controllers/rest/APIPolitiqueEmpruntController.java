package com.lms.librarymanagementsystem.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/politique-emprunt")
public class APIPolitiqueEmpruntController {
    //crud
    @GetMapping("/")
    public String getAllPolitiqueEmprunt() {
        return "Liste de politique d'emprunt";
    }
    //post
    @PostMapping("/ok/")
    public String savePolitiqueEmprunt() {
        return "Create Politique d'emprunt";
    }
}
