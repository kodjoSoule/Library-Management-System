package com.lms.librarymanagementsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LivreTest {

    private Livre livre;

    @BeforeEach
    public void setUp() {
        livre = new Livre();
    }

    @Test
    public void testGetId() {
        livre.setId(1L);
        assertEquals(1, livre.getId());
    }

    @Test
    public void testSetId() {
        livre.setId(1L);
        assertEquals(1, livre.getId());
    }

    @Test
    public void testGetIsbn() {
        livre.setIsbn("123456789");
        assertEquals("123456789", livre.getIsbn());
    }

    @Test
    public void testSetIsbn() {
        livre.setIsbn("123456789");
        assertEquals("123456789", livre.getIsbn());
    }

    @Test
    public void testGetTitre() {
        livre.setTitre("Titre");
        assertEquals("Titre", livre.getTitre());
    }

    @Test
    public void testSetTitre() {
        livre.setTitre("Titre");
        assertEquals("Titre", livre.getTitre());
    }

}