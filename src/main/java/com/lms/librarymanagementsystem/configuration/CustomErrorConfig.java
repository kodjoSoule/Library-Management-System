//package com.lms.librarymanagementsystem.configuration;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class CustomErrorConfig implements ErrorController {
//    @RequestMapping("/error")
//    public String handleError() {
//        // Affiche le template d'erreur personnalisé
//        return "error-404";
//    }
//    //exclus  routes begin by /api
//    @RequestMapping("/api/error")
//    public ResponseEntity<String> handleErrorApi() {
//        // Affiche le template d'erreur personnalisé
//        return ResponseEntity.notFound().build();
//    }
//}
