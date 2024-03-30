package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.Emprunt;
import com.lms.librarymanagementsystem.model.Infos;
import com.lms.librarymanagementsystem.model.Penalite;
import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class HomeController {
    @Autowired
    private InfosService infosService;
    @Autowired
    LivreService livreService;
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    EmpruntService empruntService;
    @Autowired
    PenaliteService penaliteService;
    @GetMapping
    public String home(Model model) {
        model.addAttribute("message", "" +
                "Bienvenue sur la page d'accueil de la bibliothèque en ligne. Vous pouvez consulter les livres disponibles, ");


        model.addAttribute("topEmprunteLivres", livreService.getTopEmprunteLivres());
        model.addAttribute("infos", infosService.getFirstInfos());
        model.addAttribute("recentEmprunts", empruntService.getThreeRecentEmprunts());
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
    //page not found for error routes

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


    @GetMapping("/admin")
    public String index(
            RedirectAttributes redirectAttributes
    ) {

        return "redirect:/admin/dashboard";
    }
    @GetMapping("/admin/dashboard")
    public String administration(Model model) {
        List<Emprunt> recentEmprunts = empruntService.getThreeRecentEmprunts();
        List<Emprunt> retardsEmprunts = empruntService.getRetardsEmprunts();
        List<Utilisateur> utilisateursRoleAdmin = utilisateurService.getUtilisateursByRole("ADMIN");
        model.addAttribute("topEmprunteLivres", livreService.getTopEmprunteLivres());
        model.addAttribute("recentEmprunts", recentEmprunts);
        model.addAttribute("livresCount", livreService.countLivres());
        model.addAttribute("utilisateursCount", utilisateurService.countUtilisateurs());
        model.addAttribute("empruntsCount", empruntService.countEmprunts());
        model.addAttribute("administrateurCount", utilisateursRoleAdmin.size());
        model.addAttribute("empruntsEnRetard", retardsEmprunts.size());
        return "admin/dashboard";
    }
    @GetMapping("/admin/livres-manager")
    public String livres(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "admin/livres-manager";
    }

    @GetMapping("/admin/notifications-manager")
    public String notifications(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }

    //access-denied
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    //user profile
    @GetMapping("/user/user-profile")
    public String userProfile(Model model, RedirectAttributes redirectAttributes) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            Utilisateur user = utilisateurService.findByUsername(username);
            if (user != null) {
                List<Penalite> penalites = penaliteService.getPenalitesByUtilisateur(user);
                if (penalites != null) {
                    model.addAttribute("penalites", penalites);
                }
                model.addAttribute("user", user);
                model.addAttribute("messages", user.getMessages());
                model.addAttribute("livresEmpruntes", empruntService.getEmpruntsByUtilisateur(user));
                return "user-profile";
            }
        }
        redirectAttributes.addFlashAttribute("message", "Vous devez vous connecter pour accéder à votre profil");
        return "redirect:/";
    }
    @GetMapping("/user/{id}/edit-profile")
    public String editProfile(@PathVariable Long id, Model model) {
        Utilisateur user = utilisateurService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "user-edit-profile";
        }
        return "redirect:/";
    }


}
