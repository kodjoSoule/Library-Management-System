package com.lms.librarymanagementsystem.controllers.rest;

import com.lms.librarymanagementsystem.model.Exemplaire;
import com.lms.librarymanagementsystem.service.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exemplaires")
public class APIExemplaireController {
    @Autowired
    private ExemplaireService exemplaireService;
    //list all exemplaires
    @GetMapping("/")
    public List<Exemplaire> getAllExemplaires() {
        return exemplaireService.getAllExemplaires();
    }
}
