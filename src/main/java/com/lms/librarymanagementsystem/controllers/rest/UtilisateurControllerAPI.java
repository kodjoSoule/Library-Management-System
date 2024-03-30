package com.lms.librarymanagementsystem.controllers.rest;

import com.lms.librarymanagementsystem.configuration.security.CustomUserDetailsService;
import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.service.MessageSender;
import com.lms.librarymanagementsystem.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UtilisateurControllerAPI {
    @Autowired
    private final UtilisateurService utilisateurService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public UtilisateurControllerAPI(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/utilisateurs")
    public ResponseEntity<Page<Utilisateur>> listUsers(@RequestParam("pageNo") Optional<Integer> page,
                                                       @RequestParam("size") Optional<Integer> size,
                                                       @RequestParam("search") Optional<String> search
    ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Utilisateur> utilisateursPage;
        if (search.isPresent()){
            utilisateursPage = utilisateurService.findByUsernameContainingIgnoreCase(search.get(), PageRequest.of(currentPage - 1, pageSize));
        }else{
            utilisateursPage = utilisateurService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        }
        return ResponseEntity.ok(utilisateursPage);
    }
    //lecture dun utilisateur
    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> findUserByID(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.getUserById(id);
        return ResponseEntity.ok(utilisateur);
    }
    //modifier un utilisateur
    @PutMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> updateUser(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateur1 = utilisateurService.getUserById(id);
        if (utilisateur1 == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(utilisateur1);
    }
    //supprimer un utilisateur
    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        utilisateurService.deleteUser(id);
        return ResponseEntity.ok("Utilisateur supprimé avec succès");
    }
    //ajouter un utilisateur
    @PostMapping("/utilisateur")
    public ResponseEntity<Utilisateur> saveUser(@RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateur1 = utilisateurService.saveUser(utilisateur);
        return ResponseEntity.ok(utilisateur1);
    }

}