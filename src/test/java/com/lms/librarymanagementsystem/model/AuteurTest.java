package com.lms.librarymanagementsystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class AuteurTest {
    private Auteur auteur;

    @BeforeEach
    void setUp() {
        auteur = new Auteur();
    }

    @Test
    void getDateNaissance() {
        LocalDate date = LocalDate.now();

        auteur.setDateNaissance(date);
        assertEquals(date, auteur.getDateNaissance());
    }

    @Test
    void setDateNaissance() {
        LocalDate date = LocalDate.now();
        auteur.setDateNaissance(date);
        assertEquals(date, auteur.getDateNaissance());
    }

    @Test
    void testToString() {
        auteur.setNom("Doe");
        auteur.setPrenom("John");
        assertEquals("Auteur{nom='Doe', prenom='John'}", auteur.toString());
    }

    @Test
    void getId() {
        auteur.setId(1L);
        assertEquals(1, auteur.getId());
    }

    @Test
    void setId() {
        auteur.setId(1L);
        assertEquals(1, auteur.getId());
    }

    @Test
    void getNom() {
        auteur.setNom("Doe");
        assertEquals("Doe", auteur.getNom());
    }

    @Test
    void setNom() {
        auteur.setNom("Doe");
        assertEquals("Doe", auteur.getNom());
    }

    @Test
    void getPrenom() {
        auteur.setPrenom("John");
        assertEquals("John", auteur.getPrenom());
    }

    @Test
    void setPrenom() {
        auteur.setPrenom("John");
        assertEquals("John", auteur.getPrenom());
    }

    @Test
    void getLivres() {
        List<Livre> livres = new ArrayList<>();
        livres.add(new Livre());
        auteur.setLivres(livres);
        assertEquals(livres, auteur.getLivres());
    }

    @Test
    void setLivres() {
        List<Livre> livres = new ArrayList<>();
        livres.add(new Livre());
        auteur.setLivres(livres);
        assertEquals(livres, auteur.getLivres());
    }

    @Test
    void getNomComplet() {
        auteur.setNom("Doe");
        auteur.setPrenom("John");
        assertEquals("John Doe", auteur.getNomComplet());
    }
}