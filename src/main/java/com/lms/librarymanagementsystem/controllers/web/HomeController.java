package com.lms.librarymanagementsystem.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "" +
                "Bienvenue sur la page d'accueil de la bibliothèque en ligne. Vous pouvez consulter les livres disponibles, ");
        return "home";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
