package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.model.LivreRequest;
import com.lms.librarymanagementsystem.service.AuteurService;
import com.lms.librarymanagementsystem.service.CategorieService;
import com.lms.librarymanagementsystem.service.LivreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
public class LivreController {
    @Autowired
    LivreService livreService;
    @Autowired
    CategorieService categorieService;
    @Autowired
    AuteurService auteurService ;
    //liste des livres
    // Endpoint pour obtenir une liste paginée de livres
    @GetMapping("/livres")
    public String getPaginatedLivres(
            @RequestParam("pageNo") Optional<Integer> page,
            @RequestParam("pageSize") Optional<Integer> size,
            Model model
    ) {
//        int currentPage = page.orElse(1);
                int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize) ;
        Page<Livre> livrePage = livreService.findPaginated(pageable);
        //List<Livre> livres = livrePage.getContent();
        if (currentPage >= livrePage.getTotalPages()) {
//            throw new IllegalArgumentException("Page number out of bounds");
            currentPage = 1;
            pageable = PageRequest.of(currentPage - 1, pageSize) ;
            livrePage = livreService.findPaginated(pageable);

        }
        int totalPages = livrePage.getTotalPages();
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", livrePage.getTotalPages());
        model.addAttribute("pageSize", livrePage.getTotalElements());
        model.addAttribute("livres", livrePage);

        //model.addAttribute("imageBase64", convertToBase64(livrePage.getImage().getImageData()));
        return "livres/listes";
    }

    @GetMapping("/admin/livres")
    public String getPaginatedLivresAdmin(
            @RequestParam("pageNo") Optional<Integer> page,
            @RequestParam("pageSize") Optional<Integer> size,
            Model model
    ) {
//        int currentPage = page.orElse(1);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize) ;
        Page<Livre> livrePage = livreService.findPaginated(pageable);
        //List<Livre> livres = livrePage.getContent();
        if (currentPage >= livrePage.getTotalPages()) {
//            throw new IllegalArgumentException("Page number out of bounds");
            currentPage = 1;
            pageable = PageRequest.of(currentPage - 1, pageSize) ;
            livrePage = livreService.findPaginated(pageable);

        }
        int totalPages = livrePage.getTotalPages();
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", livrePage.getTotalPages());
        model.addAttribute("pageSize", livrePage.getTotalElements());
        model.addAttribute("livres", livrePage);

        return "admin/livres-manager";
    }

    @GetMapping("/admin/livres/add")
    public String showAjouterLivreFormAdmin(Model model) {
        //LivreRequest
        LivreRequest livreRequest = new LivreRequest();
        livreRequest.setAuteurId(1L);

        livreRequest.setIsbn("1234567890");
        livreRequest.setLangue("fr");
        livreRequest.setNbPages(100);
        livreRequest.setEditeur("Editeur");
        livreRequest.setTitre("Titre");
        livreRequest.setDescription("Description");
        model.addAttribute("livreRequest",livreRequest);
        model.addAttribute("livre", new Livre());
        model.addAttribute("categories", categorieService.getAllCategories());
        model.addAttribute("auteurs", auteurService.getAllAuteurs() );
        return "admin/livre-form-new";
    }
    @GetMapping("/admin/livre/{id}/update")
    public String showModifierLivreFormAdmin(@PathVariable("id") Long id, Model model) {
        LivreRequest livreRequest = new LivreRequest();
        model.addAttribute("livreRequest", livreRequest);
        model.addAttribute("categories", categorieService.getAllCategories());
        model.addAttribute("auteurs", auteurService.getAllAuteurs() );
        return "admin/livre-form-update";
    }
    @GetMapping("/admin/livre/{id}/delete")
    public String supprimerLivreAdmin(@PathVariable("id") Long id) {
        livreService.supprimerLivre(id);
        return "/admin/livre-form-confirmation";
    }
    @DeleteMapping("/admin/livre/{id}/delete")
    public String deleteLivreAdmin(@PathVariable("id") Long id) {
        livreService.supprimerLivre(id);
        return "redirect:/admin/livre-form-confirmation";
    }
    @PostMapping("/admin/livres/{id}/update")
    public String modifierLivreAdmin(@PathVariable("id") Long id, @ModelAttribute("livre") Livre livre) {
        livreService.modifierLivre(id, livre);
        return "redirect:/admin/livres";
    }

    // Endpoint pour obtenir les détails d'un livre selectionné
    @GetMapping("/admin/livre/{id}")
    public String getLivreDetails(
            @PathVariable("id") Long id,
            Model model
    ) {
        Livre livre = livreService.getLivreById(id);
        Auteur auteur = livre.getAuteur();
        //byte[] imageData = livre.getImage().getImageData();
        model.addAttribute("auteur", auteur.getNom()+ " " + auteur.getPrenom());
        model.addAttribute("livre", livre);
        //model.addAttribute("imageBase64", convertToBase64(imageData));
        return "/admin/livre-details";
    }

    // Exemple de méthode dans votre contrôleur ou service
    public String convertToBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }


    @GetMapping("/ajouter/ol")
    public String showAjouterLivreForm(Model model) {
        model.addAttribute("livre", new Livre());
        return "livres/ajouterLivre";
    }
    @PostMapping("/admin/livres/add")
    public String ajouterLivre(@ModelAttribute("livre") Livre livre) {
        livreService.ajouterLivre(livre);
        return "redirect:/admin/livre-form-confirmation";
    }

    @GetMapping("/admin/livre-form-confirmation")
    public String showConfirmationForm() {
        return "admin/livre-form-confirmation";
    }
    @GetMapping("/admin/livres/{id}/modifier")
    public String showModifierLivreForm(@PathVariable("id") Long id, Model model) {
        Livre livre = livreService.getLivreById(id);
        model.addAttribute("livre", livre);
        return "livres/modifierLivre";
    }

    @PostMapping("/admin/livres/{id}/modifier")
    public String modifierLivre(@PathVariable("id") Long id, @ModelAttribute("livre") Livre livre) {
        livreService.modifierLivre(id, livre);
        return "redirect:/livres";
    }

    @GetMapping("/admin/livres/{id}/supprimer")
    public String supprimerLivre(@PathVariable("id") Long id) {
        livreService.supprimerLivre(id);
        return "redirect:/livres";
    }
}
