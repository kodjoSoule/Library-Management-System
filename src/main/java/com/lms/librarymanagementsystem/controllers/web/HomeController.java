package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.Infos;
import com.lms.librarymanagementsystem.service.InfosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Slf4j
@Controller
public class HomeController {
    @Autowired
    private InfosService infosService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("message", "" +
                "Bienvenue sur la page d'accueil de la bibliothèque en ligne. Vous pouvez consulter les livres disponibles, ");
        return "home";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/reglements")
    public String reglements(Model model) {
        model.addAttribute("infos", infosService.getFirstInfos());
        return "/reglements/reglements";
    }

    @GetMapping("/admin/infos-manager")
    public String infos(Model model) {
        Infos infos = infosService.getFirstInfos();
        model.addAttribute("infos", infos);
        return "admin/infos-manager";
    }
    @GetMapping("/admin/infos/infos-form-update")
    public String updateInfos(Model model) {
        Infos infos = infosService.getFirstInfos();
        model.addAttribute("infos", infos);
        return "admin/infos-form-update";
    }
    @PostMapping("/admin/infos/update")
    public String updateInfos(@RequestBody Infos infos, RedirectAttributes redirectAttributes) {
        if (infos == null) {
            return "redirect:/admin/infos-manager";
        }
        try{
            log.info(
                    "Updating infos with id: " + infos.getId()
            );
            redirectAttributes.addFlashAttribute("message", "Les informations ont été mises à jour avec succès");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "Erreur lors de la mise à jour des informations");

        }
        return "redirect:/admin/infos-manager";
    }

}
