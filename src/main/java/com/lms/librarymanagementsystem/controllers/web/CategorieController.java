package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.Categorie;
import com.lms.librarymanagementsystem.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
public class CategorieController {
    private final CategorieService categorieService;
    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }
    @GetMapping("/admin/categories")
    public String getAllCategories(Model model, @RequestParam("pageNo") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
            int currentPage = page.orElse(1);
            int pageSize = size.orElse(5);
        Page<Categorie> categoriePage = categorieService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("categories", categoriePage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", categoriePage.getTotalPages());
        return "/admin/categorie-manager";
    }
    @GetMapping("/admin/categories/{id}")
    public String getcategorieById(@PathVariable Long id, Model model) {
        Categorie categorie = categorieService.getcategorieById(id);
        model.addAttribute("categorie", categorie);
        return "/admin/categories/view";
    }
    @GetMapping("/admin/categorie/add")
    public String createcategorieForm(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "/admin/categorie-form-new";
    }
    @PostMapping("/admin/categorie/add")
    public String createcategorie(@ModelAttribute Categorie categorie, RedirectAttributes redirectAttributes) {
        try {
            categorieService.saveCategorie(categorie);
            redirectAttributes.addFlashAttribute("success", "La catégorie a été ajoutée avec succès");
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "La catégorie n'a pas pu être ajoutée");
        }
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categorie/{id}/update")
    public String updatecategorieForm(@PathVariable Long id, Model model) {
        Categorie categorie = categorieService.getcategorieById(id);
        if(categorie == null) {
            return "redirect:/admin/categories";
        }
        model.addAttribute("categorie", categorie);
        return "/admin/categorie-form-update";
    }
    @PostMapping("/admin/categorie/{id}/update")
    public String updatecategorie(@PathVariable Long id, @ModelAttribute Categorie categorie, RedirectAttributes redirectAttributes) {
        try{
            categorie.setId(id);
            categorieService.saveCategorie(categorie);
            redirectAttributes.addFlashAttribute("success", "La catégorie a été modifiée avec succès");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "La catégorie n'a pas pu être modifiée");
        }
        return "redirect:/admin/categories";
    }
    @PostMapping("/admin/categorie/update")
    public String updatecategorie(@ModelAttribute Categorie categorie, RedirectAttributes redirectAttributes ) {
        try {
            categorieService.saveCategorie(categorie);
            redirectAttributes.addFlashAttribute("message", "La catégorie a été ajoutée avec succès");

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "La catégorie n'a pas pu être ajoutée");
        }
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categorie/{id}/delete")
    public String deletecategorie(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categorieService.deleteCategorie(id);
            redirectAttributes.addFlashAttribute("success", "La catégorie a été supprimée avec succès");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "La catégorie n'a pas pu être supprimée");
        }
        return "redirect:/admin/categories";
    }
}