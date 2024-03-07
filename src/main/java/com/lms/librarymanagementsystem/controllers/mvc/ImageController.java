package com.lms.librarymanagementsystem.controllers.mvc;

import com.lms.librarymanagementsystem.model.ImageLivre;
import com.lms.librarymanagementsystem.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;
    // Ajoutez les méthodes pour les opérations sur les images
    public void save(ImageLivre imageLivre) {
        imageService.save(imageLivre);
    }
    public ImageLivre getImageById(Long id) {
        ImageLivre searchImageLivre = imageService.getImageById(id);
        return searchImageLivre;
    }
    public void deleteImage(Long id) {
        imageService.deleteImage(id);
    }
    public void updateImage(ImageLivre imageLivre) {
        imageService.updateImage(imageLivre);
    }
    @PostMapping(value = "/imagedisplay/add", consumes = MULTIPART_FORM_DATA_VALUE)
    public String addImageProfile(ImageLivre imageLivre, @RequestPart("file") MultipartFile file) {
        imageService.saveImagePost(imageLivre, file);
        return "redirect:/imagedisplay/view";
    }

}
