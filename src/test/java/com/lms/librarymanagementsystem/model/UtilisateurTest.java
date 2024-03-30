package com.lms.librarymanagementsystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {

    private Utilisateur utilisateur;

    @BeforeEach
    void setUp() {
        utilisateur = new Utilisateur();
    }

    @Test
    void testGetFullName() {
        // Given
        utilisateur.setPrenom("John");
        utilisateur.setNom("Doe");

        // When
        String fullName = utilisateur.getFullName();

        // Then
        assertEquals("John Doe", fullName);
    }

    @Test
    void testToString() {
        // Given
        utilisateur.setPrenom("John");
        utilisateur.setNom("Doe");

        // When
        String toString = utilisateur.toString();

        // Then
        assertTrue(toString.contains("John"));
        assertTrue(toString.contains("Doe"));
    }

    @Test
    void testSetNom() {
        // Given
        String nom = "Doe";

        // When
        utilisateur.setNom(nom);

        // Then
        assertEquals(nom, utilisateur.getLastName());
    }

    @Test
    void testSetPrenom() {
        // Given
        String prenom = "John";

        // When
        utilisateur.setPrenom(prenom);

        // Then
        assertEquals(prenom, utilisateur.getFirstName());
    }

}