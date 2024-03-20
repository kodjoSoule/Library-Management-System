//package com.lms.librarymanagementsystem.controllers.rest;
//
//import com.lms.librarymanagementsystem.model.*;
//import com.lms.librarymanagementsystem.service.CategorieService;
//
//
//import com.lms.librarymanagementsystem.model.PostRequest;
//import io.micrometer.common.util.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/categroies")
//public class CategorieControllerOLd {
//    @Autowired
//    private CategorieService categorieService;
//    @GetMapping
//    public List<Categorie> getAllCategories(@RequestParam(required = false) String nom) {
//        if (StringUtils.isEmpty(nom)) {
//            return categorieService.getAllCategories();
//        }
//        return categorieService.findByNom(nom);
//    }
//    @PostMapping
//    public String saveCategorie(@RequestBody PostRequest request) {
//        return request.toString();
//    }
//    //test post
//
//
//    //details d'un categorie
////    @GetMapping("/api/categorie/{id}")
////    public ResponseEntity<Categorie> getCategorie(@PathVariable Long id) {
////        Categorie categorie = categorieService.getCategorieById(id);
////        if (categorie == null) {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////        return new ResponseEntity<>(categorie, HttpStatus.OK);
////    }
////
////    //supprimer un categorie
////    @DeleteMapping("/api/categorie/{id}")
////    public ResponseEntity<Categorie> deleteCategorie(@PathVariable Long id) {
////        Categorie categorie = categorieService.getCategorieById(id);
////        if (categorie == null) {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////        categorieService.deleteCategorie(id);
////        return new ResponseEntity<>(categorie, HttpStatus.OK);
////    }
////
////    //modifier un categorie
////    @PutMapping("/api/categorie/{id}")
////    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
////        Categorie categorie1 = categorieService.getCategorieById(id);
////        if (categorie1 == null) {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////        categorie1.setNom(categorie.getNom());
////        categorie1.setDescription(categorie.getDescription());
////        categorieService.saveCategorie(categorie1);
////        return new ResponseEntity<>(categorie1, HttpStatus.OK);
////    }
////
////    //rechercher un categorie
////    @GetMapping("/api/categorie/search/{nom}")
////    public ResponseEntity<List<Categorie>> searchCategorie(@PathVariable String nom) {
////        List<Categorie> categories = categorieService.searchCategorie(nom);
////        if (categories.isEmpty()) {
////            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////        }
////        return new ResponseEntity<>(categories, HttpStatus.OK);
////    }
////
////    //ajouter un livre à un categorie
////    @PutMapping("/api/categorie/{id}/addLivre/{idLivre}")
////    public ResponseEntity<Categorie> addLivreToCategorie(@PathVariable Long id, @PathVariable Long idLivre) {
////        Categorie categorie = categorieService.getCategorieById(id);
////        if (categorie == null) {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////        categorieService.addLivreToCategorie(id, idLivre);
////        return new ResponseEntity<>(categorie, HttpStatus.OK);
////    }
////
////    //supprimer un livre d'un categorie
////    @PutMapping("/api/categorie/{id}/removeLivre/{idLivre}")
////    public ResponseEntity<Categorie> removeLivreFromCategorie(@PathVariable Long id, @PathVariable Long idLivre) {
////        Categorie categorie = categorieService.getCategorieById(id);
////        if (categorie == null) {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////        categorieService.removeLivreFromCategorie(id, idLivre);
////        return new ResponseEntity<>(categorie, HttpStatus.OK);
////    }
////
////    //afficher les livres d'un categorie
////    @GetMapping("/api/categorie/{id}/livres")
////    public ResponseEntity<List<Livre>> getLivresByCategorie(@PathVariable Long id) {
////        Categorie categorie = categorieService.getCategorieById(id);
////        if (categorie == null) {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////        List<Livre> livres = categorie.getLivres();
////        return new ResponseEntity<>(livres, HttpStatus.OK);
////    }
//}
