package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AuteurController {
    @Autowired
    private AuteurService auteurService;
    @GetMapping("/admin/auteurs")
    public String index(Model model, @RequestParam("pageNo") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size, @RequestParam("search") Optional<String> search
    ){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        //show log
        System.out.println("currentPage: " + currentPage);
        System.out.println("pageSize: " + pageSize);
        Page<Auteur> auteurPage ;
        if (search.isPresent()){
            auteurPage = auteurService.findByNomContainingIgnoreCase(search.get(), PageRequest.of(currentPage - 1, pageSize));
        }else{
            auteurPage = auteurService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        }
                //= auteurService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("auteurs", auteurPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", auteurPage.getTotalPages());
        return "/admin/auteurs-manager";
    }
    @GetMapping("/admin/auteur/add")
    public String add(Model model) {
        Auteur a = new Auteur();
        model.addAttribute("auteur", a);
        return "/admin/auteur-form-new";
    }

    @PostMapping("/admin/auteur/add")//OK
    public String addAuteur(@ModelAttribute("auteur") Auteur auteur, RedirectAttributes redirectAttributes){
        try{
            auteurService.saveAuteur(auteur);
            redirectAttributes.addFlashAttribute("success", "Auteur ajouté avec succès");

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout de l'auteur");
        }
        return "redirect:/admin/auteurs";
    }
    @GetMapping("/admin/auteur/{id}/delete")//ok
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try{
            auteurService.deleteAuteur(id);
            redirectAttributes.addFlashAttribute("success", "Auteur supprimé avec succès");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression de l'auteur");
        }
        return "redirect:/admin/auteurs";
    }
    @GetMapping("/admin/auteur/{id}/update")//ok
    public String update(@PathVariable int id, Model model) {
        Auteur auteur = auteurService.getAuteurById(id);
        model.addAttribute("auteur", auteur);

        return "/admin/auteur-form-update";
    }
    @PostMapping("/admin/auteur/update")//ok
    public String updateAuteur(@ModelAttribute("auteur") Auteur auteur, RedirectAttributes redirectAttributes){
        try{
            auteurService.saveAuteur(auteur);
            redirectAttributes.addFlashAttribute("success", "Auteur modifié avec succès");

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification de l'auteur");
        }
        return "redirect:/admin/auteurs";
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
    @GetMapping("/admin/auteur/{id}")
    public String show(@PathVariable int id, Model model) {
        Auteur auteur = auteurService.getAuteurById(id);
        model.addAttribute("auteur", auteur);
        return "/admin/auteur-show";
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
