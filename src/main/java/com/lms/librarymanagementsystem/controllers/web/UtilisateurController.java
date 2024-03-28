package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UtilisateurController {
    @Autowired
    private final UtilisateurService utilisateurService;
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/admin/utilisateurs-manager")
    public String listUsers(Model model, @RequestParam("pageNo") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        List<Utilisateur> users = new ArrayList<>();
        //create 10 utilisateur objects
        for (int i = 0; i < 10; i++) {
            Utilisateur user = new Utilisateur();
            user.setId((long) i);
            user.setUsername("user" + i);
            user.setEmail("user" + i + "@example.com");
            user.setPassword("password" + i);
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            users.add(user);
        }
        Page<Utilisateur> utilisateursPage = utilisateurService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", utilisateursPage.getTotalPages());
        model.addAttribute("users", users);
        return "admin/utilisateurs-manager";
    }
    @GetMapping("/admin/utilisateur/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new Utilisateur());
        return "admin/utilisateur-form-new";
    }
    @PostMapping("/admin/utilisateurs/add")
    public String addUser(@ModelAttribute("user") Utilisateur user) {
        utilisateurService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/utilisateur/{id}/update")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        //Utilisateur user = utilisateurService.getUserById(id);
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        model.addAttribute("user", user);
        return "admin/utilisateur-form-update";
    }

    @PostMapping("/admin/utilisateurs-manager/{id}/update")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") Utilisateur user) {
        utilisateurService.updateUser(id, user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/utilisateurs-manager/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        utilisateurService.deleteUser(id);
        return "redirect:/admin/users";
    }
}