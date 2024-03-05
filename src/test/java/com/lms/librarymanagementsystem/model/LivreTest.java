package com.lms.librarymanagementsystem.model;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LivreTest {

    private Livre livre;
    private Auteur auteur;

    @BeforeEach
    public void setUp() {
        auteur = new Auteur();
        auteur.setNom("Nom de l'auteur");
        livre = new Livre();
        livre.setTitre("Titre du livre");
        livre.setIsbn("1234567890");
        livre.setAuteur(auteur);
    }

    @Test
    public void testAjouterExemplaire() {
        // Given
        Exemplaire exemplaire1 = new Exemplaire();
        Exemplaire exemplaire2 = new Exemplaire();

        // When
        livre.ajouterExemplaire(exemplaire1);
        livre.ajouterExemplaire(exemplaire2);
        List<Exemplaire> exemplaires = livre.getExemplaires();

        // Then
        assertEquals(2, exemplaires.size());
        assertTrue(exemplaires.contains(exemplaire1));
        assertTrue(exemplaires.contains(exemplaire2));
    }

    @Test
    public void testNombreExemplaires() {
        // Given
        Exemplaire exemplaire1 = new Exemplaire();
        Exemplaire exemplaire2 = new Exemplaire();
        //livre.setExemplaires(Arrays.asList(exemplaire1, exemplaire2));
        //ajouter les exemplaires au livre
        livre.ajouterExemplaire(exemplaire1);
        livre.ajouterExemplaire(exemplaire2);


        // When
        int nombreExemplaires = livre.getNombreExemplaires();

        // Then
        assertEquals(2, nombreExemplaires);
    }

    @Test
    public void testEstDisponible() {
        // Given
        Exemplaire exemplaireDisponible = new Exemplaire();
        exemplaireDisponible.setStatus("Disponible");
        Exemplaire exemplaireEmprunte = new Exemplaire();
        exemplaireEmprunte.setStatus("Emprunté");
        //livre.setExemplaires(Arrays.asList(exemplaireDisponible, exemplaireEmprunte));
        //ajouter les exemplaires au livre
        livre.ajouterExemplaire(exemplaireDisponible);
        livre.ajouterExemplaire(exemplaireEmprunte);



        // When
        boolean disponible = livre.estDisponible();

        // Then
        assertTrue(disponible);

        exemplaireDisponible.setStatus("Emprunté");
        assertFalse(livre.estDisponible());
    }
}