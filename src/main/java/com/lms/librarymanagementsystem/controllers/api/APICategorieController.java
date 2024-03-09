package com.lms.librarymanagementsystem.controllers.api;

import com.lms.librarymanagementsystem.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lms.librarymanagementsystem.model.Categorie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class APICategorieController {
    @Autowired
    private CategorieService categorieService;

    @GetMapping("/v1/categories")
    public ResponseEntity<List<Categorie>> getAllCategories() {
        List<Categorie> categories = new ArrayList<>();
        categories = categorieService.getCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    //post
    @PostMapping("/v1/categorie")
    public ResponseEntity<Categorie> saveCategorie(@RequestBody  Categorie categorie) {
        Categorie savedCategorie = categorieService.saveCategorie(categorie);
        return new ResponseEntity<Categorie>(savedCategorie, HttpStatus.CREATED);
    }


}
