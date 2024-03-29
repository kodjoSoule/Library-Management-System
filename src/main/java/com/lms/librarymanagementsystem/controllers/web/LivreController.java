package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.*;
import com.lms.librarymanagementsystem.service.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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
    @Autowired
    AdminService adminService;
    @Autowired
    ImageService imageService;

    @GetMapping("/livres")
    public String getPaginatedLivres(
            Model model, @RequestParam("pageNo") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
        Page<Livre> livrePage = livreService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", livrePage.getTotalPages());
        model.addAttribute("pageSize", livrePage.getTotalElements());
        model.addAttribute("livres", livrePage);
        //model.addAttribute("imageBase64", convertToBase64(livrePage.getImage().getImageData()));
        return "livres/listes";
    }
    @GetMapping("/livre/{id}")
    public String getLivreDetailsUser(
            @PathVariable("id") Long id,
            Model model
    ) {
        Livre livre = livreService.getLivreById(id);
        Auteur auteur = livre.getAuteur();
        model.addAttribute("auteur", auteur.getNom()+ " " + auteur.getPrenom());
        model.addAttribute("livre", livre);
        return "livres/details";
    }


@GetMapping("/admin/livres")
public String getPaginatedLivresAdmin(
        @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
        @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
        Model model
) {
    // Pour s'assurer que la valeur de pageNo est au moins égale à 1
    pageNo = Math.max(1, pageNo);

    // Création de l'objet Pageable pour la pagination
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

    // Récupération de la page de livres à partir du service
    Page<Livre> livrePage = livreService.findPaginated(pageable);

    // Correction de la logique pour gérer le dépassement de la dernière page
    int totalPages = livrePage.getTotalPages();
    if (totalPages == 0) {
        return "admin/livres-manager"; // Si aucune page n'est disponible, renvoyer simplement la vue
    }
    int adjustedPageNo = Math.min(pageNo, totalPages); // S'assurer que pageNo n'est pas supérieur au nombre total de pages
    if (adjustedPageNo != pageNo) {
        pageable = PageRequest.of(adjustedPageNo - 1, pageSize); // Ajuster la pagination
        livrePage = livreService.findPaginated(pageable); // Récupérer la page ajustée
    }

    // Création de la liste des numéros de page pour la navigation
    List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());

    // Ajout des attributs au modèle
    model.addAttribute("currentPage", adjustedPageNo);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("pageSize", livrePage.getTotalElements());
    model.addAttribute("livres", livrePage.getContent());
    return "admin/livres-manager";
}
 @GetMapping("/admin/livre/{id}/update")
    public String showModifierLivreFormAdmin(@PathVariable("id") Long id, Model model) {
        Livre livre = livreService.getLivreById(id);
        LivreDetails livreDetails = new LivreDetails();
        livreDetails.setLivreId(livre.getId());
        livreDetails.setIsbn(livre.getIsbn());
        livreDetails.setTitre(livre.getTitre());
        livreDetails.setDescription(livre.getDescription());
        livreDetails.setNbPages(livre.getNbPages());
        livreDetails.setDatePublication(livre.getDatePublication());
        livreDetails.setEditeur(livre.getEditeur());
        livreDetails.setLangue(livre.getLangue());
        livreDetails.setCategorie(String.valueOf(livre.getCategorie().getId()));
        livreDetails.setAuteur(String.valueOf(livre.getAuteur().getId()));
        livreDetails.setLivre(livre);
        model.addAttribute("livreRequest", livreDetails);
        model.addAttribute("categories", categorieService.getAllCategories());
        model.addAttribute("auteurs", auteurService.getAllAuteurs() );
        return "admin/livre-form-update";
    }
    @GetMapping("/admin/livre/{id}/delete")
    public String supprimerLivreAdmin(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            livreService.supprimerLivre(id);
            redirectAttributes.addFlashAttribute("success", "Livre supprimé avec succès !");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression du livre. Veuillez réessayer.");
            return "redirect:/admin/livres";
        }
        return "redirect:/admin/livres";
    }
    @DeleteMapping("/admin/livre/{id}/delete")
    public String deleteLivreAdmin(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        livreService.supprimerLivre(id);
        redirectAttributes.addFlashAttribute("success", "Livre supprimé avec succès !");
        return "redirect:/admin/livre-form-confirmation";
    }

    // Endpoint pour obtenir les détails d'un livre selectionné
    @GetMapping("/admin/livre/{id}")
    public String getLivreDetails(
            @PathVariable("id") Long id,
            Model model
    ) {
        Livre livre = livreService.getLivreById(id);
        Auteur auteur = livre.getAuteur();
        model.addAttribute("auteur", auteur.getNom()+ " " + auteur.getPrenom());
        model.addAttribute("livre", livre);
        return "/admin/livre-details";
    }

    //Ajouter un livre

    @GetMapping("/admin/livres/add")
    public String showAjouterLivreFormAdmin(Model model) {
        LivreDetails livreDetails = new LivreDetails();
        livreDetails.setAdminID(1L);
        model.addAttribute("livreDetails", livreDetails);
        model.addAttribute("categories", categorieService.getAllCategories());
        model.addAttribute("auteurs", auteurService.getAllAuteurs() );
        return "admin/livre-form-new";
    }
    @Transactional
    @PostMapping("/admin/livres/add")
    public String ajouterLivre(@ModelAttribute("livreDetails") LivreDetails livreDetails, RedirectAttributes redirectAttributes,@RequestParam("file") MultipartFile file ) {
        log.info("Ajout d'un livre controller invoked");
        if (livreDetails == null) {
            redirectAttributes.addFlashAttribute("message", "Erreur lors de l'ajout du livre.");
            return "redirect:/admin/livres/add";
        }
        ImageData imageData= null;
        try {
             imageData = imageService.uploadImage(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Categorie categorie = null;
        Auteur auteur = null;
        Admin admin = null;
        if ("new".equals(livreDetails.getCategorie())) {
            Categorie newCategorie = new Categorie();
            newCategorie.setNom(livreDetails.getCategorie());
            categorie = categorieService.saveCategorie(newCategorie);
        } else {
            categorie = categorieService.getCategorieById(Long.parseLong(livreDetails.getCategorie()));
        }
        if ("new".equals(livreDetails.getAuteur())) {
            Auteur newAuteur = new Auteur();
            newAuteur.setNom(livreDetails.getAuteur());
            auteur = auteurService.saveAuteur(newAuteur);
        } else {
            auteur = auteurService.getAuteurById((int) Long.parseLong(livreDetails.getAuteur()));
        }

        if (categorie == null || auteur == null) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la création du livre. Veuillez réessayer.");
            return "redirect:/admin/livres/add";
        }
        admin = adminService.getAdminById(2L);
        Admin ozoAdmin = adminService.saveAdmin(admin);
        log.info("Catégorie: " + categorie.getNom());
        log.info("Auteur: " + auteur);
        log.info("Admin: " + ozoAdmin);

        Livre livre = new Livre();
        livre.setAuteur(auteur);
        livre.setCategorie(categorie);
        livre.setIsbn(livreDetails.getIsbn());
        livre.setLangue(livreDetails.getLangue());
        livre.setNbPages(livreDetails.getNbPages());
        livre.setNbExemplaires(livreDetails.getNbExemplaires());
        livre.setEditeur(livreDetails.getEditeur());
        livre.setTitre(livreDetails.getTitre());
        livre.setDatePublication(livreDetails.getDatePublication());
        livre.setDescription(livreDetails.getDescription());
        livre.setAddedBy(ozoAdmin);
        livre.setImage(imageData);
        livreService.saveLivre(livre);
        redirectAttributes.addFlashAttribute("success", "Livre ajouté avec succès !");
        return "redirect:/admin/livre-form-confirmation";
    }
    @GetMapping("/admin/livre-form-confirmation")
    public String showConfirmationDeleteSuccess(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", "Livre ajouté avec succès !");
        return "/admin/livre-form-confirmation";
    }
    @GetMapping("/admin/livre-delete-success")
    public String showConfirmationDeleteSuccess() {
        return "/admin/livre-delete-success";
    }

    @GetMapping("/admin/livres/{id}/delete")
    public String supprimerLivre(@PathVariable("id") Long id) {
        livreService.supprimerLivre(id);
        return "redirect:/livres";
    }



}
