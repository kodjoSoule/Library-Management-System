package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.Emprunt;
import com.lms.librarymanagementsystem.service.EmpruntService;
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
@RequestMapping("/admin/emprunts")
public class EmpruntController {
    @Autowired
    private EmpruntService empruntService;
    // Endpoint pour obtenir une liste paginée des emprunts
    @GetMapping("")
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
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", empruntPage.getTotalPages());
        model.addAttribute("pageSize", empruntPage.getTotalElements());
        model.addAttribute("emprunts", empruntPage);
        return "admin/emprunt-manager";
    }

    @GetMapping("/admin")
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
