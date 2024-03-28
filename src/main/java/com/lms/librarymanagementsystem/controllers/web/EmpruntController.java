package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.*;
import com.lms.librarymanagementsystem.service.EmpruntService;
import com.lms.librarymanagementsystem.service.LivreService;
import com.lms.librarymanagementsystem.service.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@Slf4j
public class EmpruntController {
    @Autowired
    private EmpruntService empruntService;
    @Autowired
    private LivreService livreService;
    @Autowired
    private UtilisateurService utilisateurService;
    @GetMapping("/admin/emprunts")
    public String getPaginatedEmprunts(
            @RequestParam("pageNo") Optional<Integer> page,
            @RequestParam("pageSize") Optional<Integer> size,
            Model model
    ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize) ;
        Page<Emprunt> empruntPage = empruntService.findPaginated(pageable);
        if (currentPage >= empruntPage.getTotalPages()) {
            currentPage = 1;
            pageable = PageRequest.of(currentPage - 1, pageSize) ;
            empruntPage = empruntService.findPaginated(pageable);
        }
        int totalPages = empruntPage.getTotalPages();
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
        log.info("empruntPage: {}", empruntPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", empruntPage.getTotalPages());
        model.addAttribute("pageSize", empruntPage.getTotalElements());
        model.addAttribute("emprunts", empruntPage.getContent());
        return "admin/emprunt-manager";
    }

    @GetMapping("/admin/emprunts/retour")
    public String getPaginatedEmpruntsRetour(
            @RequestParam("pageNo") Optional<Integer> page,
            @RequestParam("pageSize") Optional<Integer> size,
            Model model
    ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize) ;
        Page<Emprunt> empruntPage = empruntService.findPaginated(pageable);
        if (currentPage >= empruntPage.getTotalPages()) {
            currentPage = 1;
            pageable = PageRequest.of(currentPage - 1, pageSize) ;
            empruntPage = empruntService.findPaginated(pageable);
        }
        int totalPages = empruntPage.getTotalPages();
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", empruntPage.getTotalPages());
        model.addAttribute("pageSize", empruntPage.getTotalElements());
        model.addAttribute("emprunts", empruntPage);
        return "admin/emprunts-retourner-manager";
    }
    @GetMapping("/admin/emprunt/users")
    public String getPaginatedUsers(
            @RequestParam("pageNo") Optional<Integer> page,
            @RequestParam("pageSize") Optional<Integer> size,
            @RequestParam(value = "examplaireId", defaultValue = "0") int livreId,
            Model model
    ) {
        if (livreId == 0 ) {
            return "redirect:/admin/emprunt/livres";
        }
        if(!livreService.existsById((long) livreId)){
            return "redirect:/admin/emprunt/livres";
        }
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Utilisateur> utilisateursPage = utilisateurService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("livreId", livreId);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", utilisateursPage.getTotalPages());
        model.addAttribute("users", utilisateursPage.getContent());
        return "admin/emprunt-users";
    }
    @GetMapping("/admin/emprunt/livres")
    public String getPaginatedLivres(
            @RequestParam("pageNo") Optional<Integer> page,
            @RequestParam("pageSize") Optional<Integer> size,
            Model model
    ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Livre> livrePage = livreService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", livrePage.getTotalPages());
        model.addAttribute("pageSize", livrePage.getTotalElements());
        model.addAttribute("livres", livrePage);
        return "admin/emprunt-livres";
    }
    @PostMapping("/admin/emprunt/add")
    public String createEmprunt(@ModelAttribute("empruntDetails") EmpruntDetails empruntDetails, RedirectAttributes redirectAttributes) {

        log.info("empruntDetails: {}", empruntDetails);
//        emprunt.setDateRetourPrevue(emprunt.getDateEmprunt().plusDays(7));
//        emprunt.setRetourne(false);
//        Adherent adherent = new Adherent();
//        adherent = (Adherent) empruntDetails.getUtilisateur();
//        emprunt.setAdherent(adherent);
//        emprunt.setExemplaire(empruntDetails.getLivre().getExemplaires().get(0));

        Emprunt emprunt = new Emprunt();
        Utilisateur utilisateur = new Utilisateur();
        Livre sousOrange = new Livre();

        emprunt.setUtilisateur(utilisateur);
        emprunt.setExemplaire(s);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetourPrevue(LocalDate.now().plusDays(7));
        log.info("Emprunt1 créé avec succès");
        try{

            empruntService.saveEmprunt(emprunt);
            redirectAttributes.addFlashAttribute("success", "Emprunt ajouté avec succès");

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout de l'emprunt");
        }
        return "redirect:/admin/emprunt/livres";
    }
    @GetMapping("/admin/emprunt/add")
    public String createEmpruntForm(@RequestParam("livreId") int livreId ,@RequestParam("userId") int userId ,  Model model) {
        if (livreId == 0 || userId == 0) {
            return "redirect:/admin/emprunt/livres";
        }
        if(!livreService.existsById((long) livreId) || !utilisateurService.existsById((long) userId)){
            return "redirect:/admin/emprunt/livres";
        }
        Livre livre = livreService.getLivreById((long) livreId);
        log.info("livre: {}", livre);
        Utilisateur utilisateur = utilisateurService.getUserById((long) userId);
        log.info("utilisateur: {}", utilisateur);
        EmpruntDetails empruntDetails = new EmpruntDetails();
        empruntDetails.setLivreId(livre.getId());
        empruntDetails.setUtilisateurId(utilisateur.getId());
        empruntDetails.setTitreLivre(livre.getTitre());
        empruntDetails.setIsbnLivre(livre.getIsbn());
        empruntDetails.setAuteurLivre(livre.getAuteur().getNomComplet());
        empruntDetails.setEditeurLivre(livre.getEditeur());
        empruntDetails.setCategorieId(livre.getCategorie().getId());
        empruntDetails.setNomAdherent(utilisateur.getFullName());
        empruntDetails.setDateEmprunt(LocalDate.now());
        empruntDetails.setDateRetourPrevue(LocalDate.now().plusDays(7));
        empruntDetails.setRetourne(false);
        empruntDetails.setCategorieLivre(livre.getCategorie().getNom());
        model.addAttribute("empruntDetails",empruntDetails );
        return "admin/emprunt-form-new";
    }
    @GetMapping("/admin/emprunt/{id}")
    public String showEmpruntDetails(@PathVariable("id") Long id, Model model) {
        //for testing
        EmpruntDetails empruntDetails = new EmpruntDetails();
        empruntDetails.setEmpruntId(1L);
        empruntDetails.setDateEmprunt(LocalDate.now());
        empruntDetails.setDateRetourPrevue(LocalDate.now().plusDays(7));
        empruntDetails.setRetourne(false);
        empruntDetails.setNomAdherent("John Doe");
        empruntDetails.setPrenomAdherent("Jane Doe");
        empruntDetails.setRetourne(false);
        empruntDetails.setTitreLivre("Le seigneur des anneaux");
        empruntDetails.setAuteurLivre("J.R.R. Tolkien");
        empruntDetails.setIsbnLivre("978-2-07-061282-6");

        empruntDetails.setEditeurLivre("Gallimard");
        model.addAttribute("emprunt", empruntDetails);
        return "admin/emprunt-show";
    }


    @GetMapping("/admin/emprunts/admin")
    public String getPaginatedEmpruntsAdmin(
            @RequestParam("pageNo") Optional<Integer> page,
            @RequestParam("pageSize") Optional<Integer> size,
            Model model
    ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Emprunt> empruntPage = empruntService.findPaginated(pageable);
        if (currentPage >= empruntPage.getTotalPages()) {
            currentPage = 1;
            pageable = PageRequest.of(currentPage - 1, pageSize);
            empruntPage = empruntService.findPaginated(pageable);
        }
        int totalPages = empruntPage.getTotalPages();
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", empruntPage.getTotalPages());
        model.addAttribute("pageSize", empruntPage.getTotalElements());
        model.addAttribute("emprunts", empruntPage);
        return "admin/emprunt-manager";
    }

}
