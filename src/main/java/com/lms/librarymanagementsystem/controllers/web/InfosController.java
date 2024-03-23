package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.service.InfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class InfosController {
    @Autowired
    private InfosService infosService;
    @RequestMapping("/admin/infos-manager")
    public String infos(Model model) {
        model.addAttribute("infos", infosService.getFirstInfos());
        return "admin/infos-manager";
    }
    @GetMapping("/reglements")
    public String reglements(Model model) {
        model.addAttribute("infos", infosService.getFirstInfos());
        return "/reglements/reglements";
    }
}
