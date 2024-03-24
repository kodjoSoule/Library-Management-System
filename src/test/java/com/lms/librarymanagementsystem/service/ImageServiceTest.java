package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.ImageData;
import com.lms.librarymanagementsystem.repository.ImageDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ImageServiceTest {

    @InjectMocks
    ImageService imageService;

    @Mock
    ImageDataRepository imageDataRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void uploadImageTest() throws IOException {
        // Arrange
        MultipartFile file = new MockMultipartFile("file", "hello.png", "image/png", "Hello, World!".getBytes());
        ImageData imageData = new ImageData();
        imageData.setName(file.getOriginalFilename());
        imageData.setType(file.getContentType());
        when(imageDataRepository.save(any(ImageData.class))).thenReturn(imageData);

        // Act
        ImageData savedImageData = imageService.uploadImage(file);

        // Assert
        assertEquals(imageData.getName(), savedImageData.getName());
        assertEquals(imageData.getType(), savedImageData.getType());
        verify(imageDataRepository, times(1)).save(any(ImageData.class));
    }

    @Test
    public void downloadImageTest() throws IOException {
        // Arrange
        String fileName = "hello.png";

        // Act
        byte[] fileData = imageService.downloadImage(fileName);

        // Assert
        // Here you can add assertions based on what you expect the downloaded data to be
    }

    @Test
    public void getImageUrlTest() {
        // Arrange
        String fileName = "hello.png";
        ImageData imageData = new ImageData();
        imageData.setName(fileName);
        when(imageDataRepository.findByName(fileName)).thenReturn(imageData);

        // Act
        String imageUrl = imageService.getImageUrl(fileName);

        // Assert
        assertEquals(imageData.getFilePath(), imageUrl);
        verify(imageDataRepository, times(1)).findByName(fileName);
    }
}