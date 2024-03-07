package com.lms.librarymanagementsystem.model;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Date;
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
        //description
        livre.setDescription("Description du livre");
        //Nombre de pages
        livre.setNbPages(100);
        //Date de publication
        //LocalDate date "2021-01-01"
        livre.setDatePublication(Date.valueOf("2021-01-01"));
        //Editeur
        livre.setEditeur("Editeur du livre");
        //Langue
        livre.setLangue("Langue du livre");

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
    //getters and setters
    @Test
    public void testGetTitre() {
        // When
        String titre = livre.getTitre();

        // Then
        assertEquals("Titre du livre", titre);
    }
    @Test
    public void testSetTitre() {
        // When
        livre.setTitre("Nouveau titre du livre");

        // Then
        assertEquals("Nouveau titre du livre", livre.getTitre());
    }
    @Test
    public void testGetIsbn() {
        // When
        String isbn = livre.getIsbn();

        // Then
        assertEquals("1234567890", isbn);
    }
    @Test
    public void testSetIsbn() {
        // When
        livre.setIsbn("0987654321");

        // Then
        assertEquals("0987654321", livre.getIsbn());
    }
    @Test
    public void testGetDescription() {
        // When
        String description = livre.getDescription();

        // Then
        assertEquals("Description du livre", description);
    }
    @Test
    public void testSetDescription() {
        // When
        livre.setDescription("Nouvelle description du livre");

        // Then
        assertEquals("Nouvelle description du livre", livre.getDescription());
    }
    @Test
    public void testGetNbPages() {
        // When
        int nbPages = livre.getNbPages();

        // Then
        assertEquals(100, nbPages);
    }
    @Test
    public void testSetNbPages() {
        // When
        livre.setNbPages(200);

        // Then
        assertEquals(200, livre.getNbPages());
    }
    @Test
    public void testGetDatePublication() {
        // When
        java.util.Date datePublication = livre.getDatePublication();
        // Then
        assertEquals(Date.valueOf("2021-01-01"), datePublication);
    }
    @Test
    public void testSetDatePublication() {
        // When
        livre.setDatePublication(Date.valueOf("2022-01-01"));

        // Then
        assertEquals(Date.valueOf("2022-01-01"), livre.getDatePublication());
    }
    @Test

    public void testGetEditeur() {
        // When
        String editeur = livre.getEditeur();

        // Then
        assertEquals("Editeur du livre", editeur);
    }
    @Test
    public void testSetEditeur() {
        // When
        livre.setEditeur("Nouvel editeur du livre");

        // Then
        assertEquals("Nouvel editeur du livre", livre.getEditeur());
    }
    @Test
    public void testGetLangue() {
        // When
        String langue = livre.getLangue();

        // Then
        assertEquals("Langue du livre", langue);
    }
    @Test
    public void testSetLangue() {
        // When
        livre.setLangue("Nouvelle langue du livre");

        // Then
        assertEquals("Nouvelle langue du livre", livre.getLangue());
    }
    @Test
    public void testGetAuteur() {
        // When
        Auteur auteur = livre.getAuteur();

        // Then
        assertEquals("Nom de l'auteur", auteur.getNom());
    }
    @Test
    public void testSetAuteur() {
        // Given
        Auteur auteur = new Auteur();
        auteur.setNom("Nouveau nom de l'auteur");

        // When
        livre.setAuteur(auteur);

        // Then
        assertEquals("Nouveau nom de l'auteur", livre.getAuteur().getNom());
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