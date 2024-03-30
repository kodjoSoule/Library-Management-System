package com.lms.librarymanagementsystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategorieTest {
    private Categorie categorie;

    @BeforeEach
    void setUp() {
        categorie = new Categorie();
    }

    @Test
    void getId() {
        categorie.setId(1L);
        assertEquals(1, categorie.getId());
    }

    @Test
    void setId() {
        categorie.setId(1L);
        assertEquals(1, categorie.getId());
    }

    @Test
    void getNom() {
        categorie.setNom("Fiction");
        assertEquals("Fiction", categorie.getNom());
    }

    @Test
    void setNom() {
        categorie.setNom("Fiction");
        assertEquals("Fiction", categorie.getNom());
    }

    @Test
    void getDescription() {
        categorie.setDescription("Fiction books");
        assertEquals("Fiction books", categorie.getDescription());
    }

    @Test
    void setDescription() {
        categorie.setDescription("Fiction books");
        assertEquals("Fiction books", categorie.getDescription());
    }

    @Test
    void getLivres() {
        List<Livre> livres = new ArrayList<>();
        livres.add(new Livre());
        categorie.setLivres(livres);
        assertEquals(livres, categorie.getLivres());
    }

    @Test
    void setLivres() {
        List<Livre> livres = new ArrayList<>();
        livres.add(new Livre());
        categorie.setLivres(livres);
        assertEquals(livres, categorie.getLivres());
    }
}