package com.lms.librarymanagementsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lms.librarymanagementsystem.model.Auteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AuteurTest {

    private Auteur auteur;

    @BeforeEach
    public void setUp() {
        auteur = new Auteur();
    }

    @Test
    public void testGetId() {
        auteur.setId(1L);
        assertEquals(1, auteur.getId());
    }

    @Test
    public void testSetId() {
        auteur.setId(1L);
        assertEquals(1, auteur.getId());
    }

    @Test
    public void testGetNom() {
        auteur.setNom("Doe");
        assertEquals("Doe", auteur.getNom());
    }

    @Test
    public void testSetNom() {
        auteur.setNom("Doe");
        assertEquals("Doe", auteur.getNom());
    }

    @Test
    public void testGetPrenom() {
        auteur.setPrenom("John");
        assertEquals("John", auteur.getPrenom());
    }

    @Test
    public void testSetPrenom() {
        auteur.setPrenom("John");
        assertEquals("John", auteur.getPrenom());
    }

    @Test
    public void testGetDateNaissance() {
        LocalDate date = LocalDate.of(1990, 1, 1);
        auteur.setDateNaissance(date);
        assertEquals(date, auteur.getDateNaissance());
    }

    @Test
    public void testSetDateNaissance() {
        LocalDate date = LocalDate.of(1990, 1, 1);
        auteur.setDateNaissance(date);
        assertEquals(date, auteur.getDateNaissance());
    }

    @Test
    public void testGetNomComplet() {
        auteur.setNom("Doe");
        auteur.setPrenom("John");
        assertEquals("Doe John", auteur.getNomComplet());
    }
}