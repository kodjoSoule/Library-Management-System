package com.lms.librarymanagementsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lms.librarymanagementsystem.model.CategorieRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategorieRequestTest {

    private CategorieRequest categorieRequest;

    @BeforeEach
    public void setUp() {
        categorieRequest = new CategorieRequest();
    }

    @Test
    public void testGetNom() {
        categorieRequest.setNom("Fiction");
        assertEquals("Fiction", categorieRequest.getNom());
    }

    @Test
    public void testSetNom() {
        categorieRequest.setNom("Fiction");
        assertEquals("Fiction", categorieRequest.getNom());
    }

    @Test
    public void testGetDescription() {
        categorieRequest.setDescription("Math");
        assertEquals("Math", categorieRequest.getDescription());
    }

    @Test
    public void testSetDescription() {
        categorieRequest.setDescription("Math");
        assertEquals("Math", categorieRequest.getDescription());
    }
}