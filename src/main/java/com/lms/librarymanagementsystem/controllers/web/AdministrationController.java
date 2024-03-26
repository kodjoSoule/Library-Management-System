package com.lms.librarymanagementsystem.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdministrationController {
    @GetMapping("")
    public String index(

    ) {

        return "admin/dashboard";
    }
    @GetMapping("dashboard")
    public String administration(Model model) {
        model.addAttribute("mainContent", "dashboard");
        return "admin/dashboard";
    }
    @GetMapping("/livres-manager")
    public String livres(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "admin/livres-manager";
    }
    @GetMapping("/emprunts-manager")
    public String emprunts(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }

    @GetMapping("/categories-manager")
    public String categories(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }


    @GetMapping("/langues-manager")
    public String langues(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }
    @GetMapping("/themes-manager")
    public String themes(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }
    @GetMapping("/localisations-manager")
    public String localisations(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }
    @GetMapping("/roles-manager")
    public String roles(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }
    @GetMapping("/permissions-manager")
    public String permissions(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }
    @GetMapping("/parametres-manager")
    public String parametres(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }
    @GetMapping("/notifications-manager")
    public String notifications(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }
    @GetMapping("/logs-manager")
    public String logs(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }
    @GetMapping("/backup-manager")
    public String backup(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }
    @GetMapping("/settings-manager")
    public String settings(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }
    @GetMapping("/profile-manager")
    public String profile(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }
    @GetMapping("/logout")
    public String logout(
            Model model
    ) {
        model.addAttribute("mainContent", "dashboard");
        return "dashboard-layout";
    }

}
