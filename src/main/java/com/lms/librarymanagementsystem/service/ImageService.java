package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Image;
import com.lms.librarymanagementsystem.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService  {
    @Autowired
    private ImageRepository imageRepository;

    // Ajoutez les méthodes pour les opérations sur les images
    public void save(Image image) {
        imageRepository.save(image);
    }
    public Image getImageById(Long id) {
        Image searchImage = imageRepository.findById(id).orElse(null);
        return searchImage;
    }
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
    public void updateImage(Image image) {
        imageRepository.save(image);
    }
//    public Image getImageByLivreId(Long id) {
//        Image searchImage = imageRepository.findByLivreId(id);
//        return searchImage;
//    }
//    public Image getImageByLivreIdAndImageId(Long livreId, Long imageId) {
//        Image searchImage = imageRepository.findByLivreIdAndId(livreId, imageId);
//        return searchImage;
//    }
//    public void deleteImageByLivreId(Long id) {
//        imageRepository.deleteByLivreId(id);
//    }
//    public void deleteImageByLivreIdAndImageId(Long livreId, Long imageId) {
//        imageRepository.deleteByLivreIdAndId(livreId, imageId);
//    }
//    public void updateImageByLivreIdAndImageId(Long livreId, Long imageId, Image image) {
//        imageRepository.updateByLivreIdAndId(livreId, imageId, image);
//    }

}
