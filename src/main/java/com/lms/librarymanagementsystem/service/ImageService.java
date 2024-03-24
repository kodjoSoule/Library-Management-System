package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.ImageData;
import com.lms.librarymanagementsystem.repository.ImageDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class ImageService {
    private final ImageDataRepository imageDataRepository;
    private final Path rootLocation; // Ce chemin devra être configuré

    private static final String IMAGE_FOLDER = "src/main/resources/static/images";
    private static final String IMAGE_URL_PREFIX = "/images/";
    @Autowired
    public ImageService(ImageDataRepository imageDataRepository) {
        this.imageDataRepository = imageDataRepository;
        this.rootLocation = Paths.get(IMAGE_FOLDER);
    }
    public ImageData uploadImage(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        //Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
        Path path = this.rootLocation.resolve(filename);
        Files.write(path, file.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ImageData imageData = new ImageData();
        imageData.setName(filename);
        imageData.setType(file.getContentType());
        imageData.setFilePath(rootLocation.resolve(filename).toString());
        return imageDataRepository.save(imageData);
    }

    public byte[] downloadImage(String fileName) throws IOException {
        Path file = rootLocation.resolve(fileName);
        return Files.readAllBytes(file);
    }

    public String getImageUrl(String fileName) {
        ImageData imageData = imageDataRepository.findByName(fileName);
        if (imageData != null) {
            return imageData.getFilePath();
        }
        return null;
    }
}