//
//package com.lms.librarymanagementsystem.controllers.web;
//import com.lms.librarymanagementsystem.service.ImageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@Controller
//@RequestMapping("/images")
//public class ImageController {
//
//    @Autowired
//    private ImageService imageService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
//        try {
//            String result = imageService.uploadImage(file);
//            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Erreur lors du téléchargement de l'image: " + e.getMessage());
//        }
//    }
//
//    @GetMapping("/download/{fileName}")
//    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
//        try {
//            byte[] imageBytes = imageService.downloadImage(fileName);
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/url/{fileName}")
//    public ResponseEntity<String> getImageUrl(@PathVariable String fileName) {
//        String imageUrl = imageService.getImageUrl(fileName);
//        return ResponseEntity.ok(imageUrl);
//    }
//}