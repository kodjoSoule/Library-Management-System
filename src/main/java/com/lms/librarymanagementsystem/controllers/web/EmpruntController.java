package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.*;
import com.lms.librarymanagementsystem.service.AdminService;
import com.lms.librarymanagementsystem.service.EmpruntService;
import com.lms.librarymanagementsystem.service.LivreService;
import com.lms.librarymanagementsystem.service.UtilisateurService;
import jakarta.transaction.Transactional;
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
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/emprunts")
    public String getPaginatedEmprunts(
            @RequestParam("pageNo") Optional<Integer> page,
            @RequestParam("pageSize") Optional<Integer> size,
            @RequestParam("all") Optional<Boolean> all,
            Model model
    ) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize) ;
        Page<Emprunt> empruntPage = empruntService.findPaginated(pageable);
        //Liste des emprunt en cours
        if (all.orElse(false)) {
            empruntPage = empruntService.findEmpruntEnCoursPaginated(pageable);

        } else {
            empruntPage = empruntService.findPaginated(pageable);
        }

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
        Page<Emprunt> empruntRetournerPage = empruntService.findEmpruntRetournerPaginated(PageRequest.of(currentPage - 1, pageSize));
        log.info("**********************************");
        log.info("**********************************");
        log.info("**********************************");
        log.info("empruntRetournerPage: {}", empruntRetournerPage.getContent());

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", empruntRetournerPage.getTotalPages());
        model.addAttribute("emprunts", empruntRetournerPage.getContent());
        return "admin/emprunts-retourner-manager";
    }
    @GetMapping("/admin/emprunt/retour/{id}")
    public String getPaginatedEmpruntsRetournerUnLivre(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        if (!empruntService.existsById(id)) {
            redirectAttributes.addFlashAttribute("error", "Emprunt non trouvé");
            return "redirect:/admin/emprunts";
        }
        Emprunt emprunt = empruntService.getEmpruntById(id);
        emprunt.setRetourne(true);
        emprunt.setDateRetourEffectif(LocalDate.now());
        try{
            empruntService.saveEmprunt(emprunt);
            redirectAttributes.addFlashAttribute("success", "Emprunt retourné avec succès");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Erreur lors du retour de l'emprunt");
        }
        return "redirect:/admin/emprunts";
    }

    @GetMapping("/admin/emprunt/users")
    public String getPaginatedUsers(
            @RequestParam("pageNo") Optional<Integer> page,
            @RequestParam("pageSize") Optional<Integer> size,
            @RequestParam(value = "livreId", defaultValue = "0") int livreId,
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
    @Transactional
    @PostMapping("/admin/emprunt/add")
    public String createEmprunt(@ModelAttribute("emprunt") Emprunt emprunt, RedirectAttributes redirectAttributes) {
        log.info("**************************************************");
        log.info("**************************************************");
        log.info("**************************************************");
        log.info("**************************************************");
        log.info("**************************************************");
        log.info("**************************************************");
        log.info("**************************************************");
        log.info("emprunt: {}", emprunt);
        try{
            Utilisateur utilisateur = utilisateurService.getUserById((long) emprunt.getUtilisateurId());
            Livre livre = livreService.getLivreById((long)emprunt.getLivreId());
            Admin admin = adminService.getAdminById((long) emprunt.getAdminId());
            emprunt.setUtilisateur(utilisateur);
            emprunt.setLivre(livre);
            emprunt.setAdmin(admin);
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
        Emprunt emprunt = new Emprunt();
        emprunt.setLivre(livre);
        emprunt.setUtilisateur(utilisateur);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetourPrevue(LocalDate.now().plusDays(7));
        emprunt.setRetourne(false);
        emprunt.setDateRetourEffectif(null);
        model.addAttribute("emprunt", emprunt);
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
