package com.lms.librarymanagementsystem.controllers.mvc;

import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.service.api.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/livres")
public class LivreController {
    @Autowired
    LivreService livreService;
    //liste des livres
    @GetMapping("/ol")
    public String getAllLivres(
            Model model
    ) {
        List<Livre> livres = livreService.getAllLivres();
        System.out.println(livres);
        model.addAttribute("livres", livres);
        return "livres/listes";
    }
    // Endpoint pour obtenir une liste paginée de livres
    @GetMapping("")
    public String getPaginatedLivres(
            @RequestParam("pageNo") Optional<Integer> page,
            @RequestParam("pageSize") Optional<Integer> size,
            Model model
    ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize) ;
        Page<Livre> livrePage = livreService.findPaginated(pageable);
        //List<Livre> livres = livrePage.getContent();
        if (currentPage >= livrePage.getTotalPages()) {
            throw new IllegalArgumentException("Page number out of bounds");
        }
        int totalPages = livrePage.getTotalPages();
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", livrePage.getTotalPages());
        model.addAttribute("pageSize", livrePage.getTotalElements());
        model.addAttribute("livres", livrePage);
        return "livres/paginated";
    }

}
