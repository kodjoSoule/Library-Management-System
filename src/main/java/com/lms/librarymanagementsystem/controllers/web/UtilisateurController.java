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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                            @RequestParam("size") Optional<Integer> size,
                            @RequestParam("search") Optional<String> search
    ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Utilisateur> utilisateursPage ;
        if (search.isPresent()){
            utilisateursPage = utilisateurService.findByUsernameContainingIgnoreCase(search.get(), PageRequest.of(currentPage - 1, pageSize));
        }else{
            utilisateursPage = utilisateurService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        }
        //= utilisateurService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", utilisateursPage.getTotalPages());
        model.addAttribute("users", utilisateursPage.getContent());
        return "admin/utilisateurs-manager";
    }
    @GetMapping("/admin/utilisateur/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new Utilisateur());
        return "admin/utilisateur-form-new";
    }
    @PostMapping("/admin/utilisateur/add")
    public String addUser(@ModelAttribute("user") Utilisateur user, RedirectAttributes redirectAttributes) {
        try{
            utilisateurService.saveUser(user);
            redirectAttributes.addFlashAttribute("success", "Utilisateur ajouté avec succès");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout de l'utilisateur");
        }
        return "redirect:/admin/utilisateurs-manager";
    }

    @GetMapping("/admin/utilisateur/{id}/update")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        Utilisateur user = utilisateurService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/utilisateur-form-update";
    }

    @PostMapping("/admin/utilisateur/update")
    public String updateUser(@ModelAttribute("user") Utilisateur user, RedirectAttributes redirectAttributes) {
        //verifie if password and password confirmation are the same
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("error", "Les mots de passe ne correspondent pas");
            return "redirect:/admin/utilisateur/" + user.getId() + "/update";
        }
        try {
            utilisateurService.updateUser(user);
            redirectAttributes.addFlashAttribute("success", "Utilisateur mis à jour avec succès");

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la mise à jour de l'utilisateur");
            return "redirect:/admin/utilisateurs-manager";
        }

        return "redirect:/admin/utilisateurs-manager";
    }

    @GetMapping("/admin/utilisateurs-manager/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        utilisateurService.deleteUser(id);
        return "redirect:/admin/users";
    }
    //delete utilisateur/{id}/delete
    @GetMapping("/admin/utilisateur/{id}/delete")
    public String deleteUtilisateur(@PathVariable("id") Long id , RedirectAttributes redirectAttributes) {

        try{
            utilisateurService.deleteUser(id);
            redirectAttributes.addFlashAttribute("success", "Utilisateur supprimé avec succès");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de l'utilisateur");
        }
        return "redirect:/admin/utilisateurs-manager";
    }
}