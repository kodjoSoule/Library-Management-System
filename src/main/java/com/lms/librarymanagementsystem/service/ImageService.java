package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.ImageData;
import com.lms.librarymanagementsystem.repository.ImageDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String IMAGE_FOLDER = "src/main/resources/static/images"; // Mettre à jour si nécessaire
    private static final String IMAGE_URL_PREFIX = "/images/"; // Mettre à jour si nécessaire

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path uploadPath = Path.of(IMAGE_FOLDER).resolve(fileName);
        Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

        ImageData imageData = new ImageData();
        imageData.setName(fileName);
        imageData.setType(file.getContentType());
        imageData.setFilePath(IMAGE_URL_PREFIX + fileName);
        imageDataRepository.save(imageData);

        return "Fichier téléchargé avec succès: " + fileName;
    }

    public byte[] downloadImage(String fileName) throws IOException {
        Optional<ImageData> imageData = imageDataRepository.findByName(fileName);
        if (imageData.isEmpty()) {
            throw new IOException("Image non trouvée: " + fileName);
        }

        Path filePath = Path.of(IMAGE_FOLDER).resolve(fileName);
        return Files.readAllBytes(filePath);
    }

    public String getImageUrl(String fileName) {
        return IMAGE_URL_PREFIX + fileName;
    }
    public String uploadImageFromURL(String imageUrl) throws IOException {
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        Path uploadPath = Path.of(IMAGE_FOLDER).resolve(fileName);

        if (imageUrl.startsWith("http")) {
            URL url = new URL(imageUrl);
            try (InputStream in = url.openStream()) {
                Files.copy(in, uploadPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } else { // Si c'est un chemin de fichier local
            Files.copy(Path.of(imageUrl), uploadPath, StandardCopyOption.REPLACE_EXISTING);
        }

        return "Fichier téléchargé avec succès à partir de l'URL: " + imageUrl;
    }


}