
package com.lms.librarymanagementsystem.controllers.rest;
import com.lms.librarymanagementsystem.model.ImageData;
import com.lms.librarymanagementsystem.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageControllerAPI {
    @Autowired
    private ImageService imageService;
    @PostMapping("/api/images/upload")
    public ResponseEntity<String> uploadImage(@RequestBody ImageData imageData, @RequestParam("file") MultipartFile file) {
        try {
            ImageData result = imageService.uploadImage(file);
            if(result != null){
                return ResponseEntity.ok("Image téléchargée avec succès: " + result.getName());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors du téléchargement de l'image: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body("Erreur lors du téléchargement de l'image");
    }
    @GetMapping("/api/images/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        try {
            byte[] imageBytes = imageService.downloadImage(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/api/images/url/{fileName}")
    public ResponseEntity<String> getImageUrl(@PathVariable String fileName) {
        String imageUrl = imageService.getImageUrl(fileName);
        return ResponseEntity.ok(imageUrl);
    }
}