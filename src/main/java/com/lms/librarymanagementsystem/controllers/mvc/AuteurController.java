package com.lms.librarymanagementsystem.controllers.mvc;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuteurController {
    @Autowired
    private AuteurService auteurService;
@RequestMapping("/admin/auteurs-manager")
    public String index(Model model) {
    model.addAttribute("loading", true);
    List<Auteur> auteurs = auteurService.getAllAuteurs();
    model.addAttribute("auteurs", auteurs);
    model.addAttribute("mainContent", "admin/auteur/auteurs-manager");
    model.addAttribute("loading", false);
    return "/admin/auteurs-manager";
    }
    @GetMapping("/admin/auteurs-manager/add")
    public String add(Model model) {
        // Avant de charger les données
        Auteur a = new Auteur();
        model.addAttribute("auteur", a);
        // Une fois les données chargées

        return "/admin/auteur-form-new";
    }
    @PostMapping("/admin/auteurs-manager/add")
    public String addAuteur(@ModelAttribute("auteur") Auteur auteur){

        auteurService.saveAuteur(auteur);
        return "redirect:/admin/auteurs-manager";
    }
    @GetMapping("/admin/auteurs-manager/delete/{id}")
    public String delete(@PathVariable Long id) {
        auteurService.deleteAuteur(id);
        return "redirect:/admin/auteurs-manager";
    }
    @RequestMapping("/admin/auteurs-manager/edit")
    public String edit() {
        return "admin/auteurs-manager";
    }
    @GetMapping("/admin/auteur-confirm-delete/{id}")
    public String confirmDelete(@PathVariable("id") int auteurId, Model model) {
        // Ajoutez l'ID de l'auteur au modèle pour l'utiliser dans le template Thymeleaf
        if(auteurService.getAuteurById(auteurId) != null) {
            model.addAttribute("auteurId", auteurId);
        }
        return "admin/auteur-confirm-delete";
    }

    @RequestMapping("/show")
    public String show() {
        return "admin/auteurs-manager";
    }
    @RequestMapping("/list")
    public String list() {
        return "admin/auteurs-manager";
    }
}
