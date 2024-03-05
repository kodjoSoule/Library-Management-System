package com.lms.librarymanagementsystem.controllers.api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class APIHomeController {
    @GetMapping("")
    public String home() {
        return new String ("Welcome to the Library Management System API!");
    }
    @GetMapping("/home")
    public String home2() {
        return new String ("Welcome to the home page of the Library Management System API!");
    }


}
