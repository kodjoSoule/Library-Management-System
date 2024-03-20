package com.lms.librarymanagementsystem.controllers.rest;



import com.lms.librarymanagementsystem.model.Categorie;
import com.lms.librarymanagementsystem.model.CategorieRequest;
import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.model.ResponseMessage;
import com.lms.librarymanagementsystem.service.CategorieService;
import com.lms.librarymanagementsystem.service.PostService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.rmi.server.LogStream.log;

@RestController
@RequestMapping("/api" )
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping("/categorie/{id}")
    public ResponseEntity<Categorie> findCategorieByID(@PathVariable String id) {
        Optional<Categorie> categorie = categorieService.findById(id);
        return categorie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    //modifier une categorie
    @PutMapping("/categorie/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody CategorieRequest request) {

        Categorie categorie1 = categorieService.getCategorieById(id);
        if (categorie1 == null) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        categorie1.setNom(request.getNom());
        categorie1.setDescription(request.getDescription());
        categorieService.saveCategorie(categorie1);
        return new ResponseEntity<>(categorie1, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/categories")
    public List<Categorie> getAllCategories(@RequestParam(required = false) String nom) {
        if (StringUtils.isEmpty(nom)) {
            return categorieService.getAllCategories();
        }
        return categorieService.findByNom(nom);
    }

    @PostMapping("/categorie")
    public String saveCategorie(@RequestBody CategorieRequest request) {
        Categorie categorie = new Categorie();
        categorie.setNom(request.getNom());
        categorie.setDescription(request.getDescription());
        categorieService.saveCategorie(categorie);
        if(request.getNom() != null)
        {
            return request.getNom();
        }
        return "null";
    }
    //supprimer un categorie
    @DeleteMapping("/categorie/{id}")
    public ResponseEntity<Categorie> deleteCategorie(@PathVariable Long id) {
        Categorie categorie = categorieService.getCategorieById(id);
        if (categorie == null) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        categorieService.deleteCategorie(id);
        return new ResponseEntity<>(categorie,HttpStatusCode.valueOf(200));
    }


    //rechercher un categorie
    @GetMapping("/api/categorie/search/{nom}")
    public ResponseEntity<List<Categorie>> searchCategorie(@PathVariable String nom) {
        List<Categorie> categories = categorieService.searchCategorie(nom);
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));
        }
        return new ResponseEntity<>(categories, HttpStatusCode.valueOf(200));
    }

    //ajouter un livre à un categorie
    @PutMapping("/categorie/{id}/addLivre/{idLivre}")
    public ResponseEntity<ResponseMessage> addLivreToCategorie(@PathVariable Long id, @PathVariable Long idLivre) {
        log("/categorie/{id}/addLivre/{idLivre} Invoked");
        Categorie categorie = categorieService.getCategorieById(id);
        if (categorie == null) {
            return new ResponseEntity<>(new ResponseMessage("Categorie not found"), HttpStatusCode.valueOf(404));
        }
        boolean isAdded = categorieService.addLivreToCategorie(categorie, idLivre);
        if (!isAdded) {
            return new ResponseEntity<>(new ResponseMessage("Livre not found"), HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(new ResponseMessage("Livre added to Categorie"+categorie.getNom()), HttpStatusCode.valueOf(200));
    }

    //supprimer un livre d'un categorie
    @PutMapping("/api/categorie/{id}/removeLivre/{idLivre}")
    public ResponseEntity<Categorie> removeLivreFromCategorie(@PathVariable Long id, @PathVariable Long idLivre) {
        Categorie categorie = categorieService.getCategorieById(id);
        if (categorie == null) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        categorieService.removeLivreFromCategorie(id, idLivre);
        return new ResponseEntity<>(categorie, HttpStatusCode.valueOf(200));
    }

    //afficher les livres d'un categorie
    @GetMapping("/api/categorie/{id}/livres")
    public ResponseEntity<List<Livre>> getLivresByCategorie(@PathVariable Long id) {
        Categorie categorie = categorieService.getCategorieById(id);
        if (categorie == null) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        List<Livre> livres = categorie.getLivres();
        return new ResponseEntity<>(livres, HttpStatusCode.valueOf(200));
    }
}
