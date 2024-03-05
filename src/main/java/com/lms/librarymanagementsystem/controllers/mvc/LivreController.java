package com.lms.librarymanagementsystem.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livres")
public class LivreController {
    @GetMapping("/")
    public String getAllLivres() {
        return "Liste de livres";
    }
}
