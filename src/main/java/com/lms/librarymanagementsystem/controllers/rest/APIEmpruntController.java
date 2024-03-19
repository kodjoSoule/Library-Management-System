package com.lms.librarymanagementsystem.controllers.rest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emprunts")
@Qualifier("api")
public class APIEmpruntController {
    // Ajoutez les méthodes pour les opérations sur les emprunts
}
