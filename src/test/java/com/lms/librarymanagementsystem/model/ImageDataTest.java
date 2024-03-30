package com.lms.librarymanagementsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImageDataTest {
    private ImageData imageData;

    @BeforeEach
    public void setUp() {
        imageData = new ImageData();
    }

    @Test
    public void testGetId() {
        imageData.setId(1L);
        assertEquals(1, imageData.getId());
    }

    @Test
    public void testSetId() {
        imageData.setId(1L);
        assertEquals(1, imageData.getId());
    }

    @Test
    public void testGetName() {
        imageData.setName("ImageName");
        assertEquals("ImageName", imageData.getName());
    }

    @Test
    public void testSetName() {
        imageData.setName("ImageName");
        assertEquals("ImageName", imageData.getName());
    }

    @Test
    public void testGetType() {
        imageData.setType("ImageType");
        assertEquals("ImageType", imageData.getType());
    }

    @Test
    public void testSetType() {
        imageData.setType("ImageType");
        assertEquals("ImageType", imageData.getType());
    }

    @Test
    public void testGetFilePath() {
        imageData.setFilePath("FilePath");
        assertEquals("FilePath", imageData.getFilePath());
    }

    @Test
    public void testSetFilePath() {
        imageData.setFilePath("FilePath");
        assertEquals("FilePath", imageData.getFilePath());
    }
}