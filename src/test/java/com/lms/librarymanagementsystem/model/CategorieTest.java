package com.lms.librarymanagementsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lms.librarymanagementsystem.model.Categorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategorieTest {

    private Categorie categorie;

    @BeforeEach
    public void setUp() {
        categorie = new Categorie();
    }

    @Test
    public void testGetId() {
        categorie.setId(1L);
        assertEquals(1, categorie.getId());
    }

    @Test
    public void testSetId() {
        categorie.setId(1L);
        assertEquals(1, categorie.getId());
    }

    @Test
    public void testGetNom() {
        categorie.setNom("Fiction");
        assertEquals("Fiction", categorie.getNom());
    }

    @Test
    public void testSetNom() {
        categorie.setNom("Fiction");
        assertEquals("Fiction", categorie.getNom());
    }

    @Test
    public void testGetDescription() {
        categorie.setDescription("Physique");
        assertEquals("Physique", categorie.getDescription());
    }

    @Test
    public void testSetDescription() {
        categorie.setDescription("Physique");
        assertEquals("Physique", categorie.getDescription());
    }
}