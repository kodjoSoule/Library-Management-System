package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.ImageLivre;
import com.lms.librarymanagementsystem.repository.ImageRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageController {

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @Autowired
    private ImageRepository imageRepository;
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIRECTORY));
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'exception si nécessaire (log, throw runtime exception, etc.)
        }
    }
    @GetMapping("/uploadimage")
    public String showGallery(Model model) {
        model.addAttribute("images", imageRepository.findAll());
        return "gallery";
    }

//    @PostMapping("/upload")
//    public String uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("title") String title) throws IOException {
//        if (!file.isEmpty()) {
//            String fileName = file.getOriginalFilename();
//            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fileName);
//            Files.write(fileNameAndPath, file.getBytes());
//            ImageLivre image = new ImageLivre();
//            image.setFileName(fileName);
//            image.setTitle(title);
//            imageRepository.save(image);
//        }
//        return "redirect:/";
//    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("title") String title) throws IOException {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fileName);
            Files.write(fileNameAndPath, file.getBytes());
            //show the image uri
            System.out.println("Image URI: " + fileNameAndPath);
            ImageLivre image = new ImageLivre();
            image.setFileName(fileName);
            image.setUri(fileNameAndPath.toString());
            image.setTitle(title);
            imageRepository.save(image);
        }
        return "redirect:/uploadimage";
    }


    // Ajoutez les méthodes pour la recherche, l'édition et la suppression selon vos besoins
}
