package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.ImageLivre;
import com.lms.librarymanagementsystem.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ImageService  {
    @Autowired
    private ImageRepository imageRepository;

    // Ajoutez les méthodes pour les opérations sur les images
    public void save(ImageLivre imageLivre) {
        imageRepository.save(imageLivre);
    }
    public ImageLivre getImageById(Long id) {
        ImageLivre searchImageLivre = imageRepository.findById(id).orElse(null);
        return searchImageLivre;
    }
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
    public void updateImage(ImageLivre imageLivre) {
        imageRepository.save(imageLivre);
    }

    public void saveImagePost(ImageLivre imageLivreEntity, MultipartFile file) {
        try {
            imageLivreEntity.setImageData(file.getBytes());
            imageRepository.save(imageLivreEntity);
        } catch (IOException ex) {
            Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, null, ex);
        }

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
