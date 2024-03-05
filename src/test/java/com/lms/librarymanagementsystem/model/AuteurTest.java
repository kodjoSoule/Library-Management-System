package com.lms.librarymanagementsystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuteurTest {
    private Auteur auteur;

    @BeforeEach
    void setUp() {
        auteur = new Auteur();

    }
    @Test
    void setId() {
        // Given
        Long id = 2L;

        // When
        auteur.setId(id);

        // Then
        assertEquals(id, auteur.getId());
    }

    @Test
    void getNom() {
        // Given
        String nom = "Nom de l'auteur";
        auteur.setNom(nom);

        // When
        String nomAuteur = auteur.getNom();

        // Then
        assertEquals(nom, nomAuteur);
    }

    @Test
    void setNom() {
        // Given
        String nom = "Nom de l'auteur";

        // When
        auteur.setNom(nom);

        // Then
        assertEquals(nom, auteur.getNom());
    }

    @Test
    void getPrenom() {
        // Given
        String prenom = "Prénom de l'auteur";
        auteur.setPrenom(prenom);

        // When
        String prenomAuteur = auteur.getPrenom();

        // Then
        assertEquals(prenom, prenomAuteur);
    }

    @Test
    void setPrenom() {
        // Given
        String prenom = "Prénom de l'auteur";

        // When
        auteur.setPrenom(prenom);

        // Then
        assertEquals(prenom, auteur.getPrenom());
    }

    @Test
    void getNationalite() {
        // Given
        String nationalite = "Nationalité de l'auteur";
        auteur.setNationalite(nationalite);

        // When
        String nationaliteAuteur = auteur.getNationalite();

        // Then
        assertEquals(nationalite, nationaliteAuteur);
    }

    @Test
    void setNationalite() {
        // Given
        String nationalite = "Nationalité de l'auteur";

        // When
        auteur.setNationalite(nationalite);

        // Then
        assertEquals(nationalite, auteur.getNationalite());
    }

    @Test
    void getBiographie() {
        // Given
        String biographie = "Biographie de l'auteur";
        auteur.setBiographie(biographie);

        // When
        String biographieAuteur = auteur.getBiographie();

        // Then
        assertEquals(biographie, biographieAuteur);
    }

    @Test
    void setBiographie() {
        // Given
        String biographie = "Biographie de l'auteur";

        // When
        auteur.setBiographie(biographie);

        // Then
        assertEquals(biographie, auteur.getBiographie());
    }

    @Test
    void getDateNaissance() {
        // Given
        LocalDate dateNaissance = LocalDate.of(1980, 1, 1);
        auteur.setDateNaissance(dateNaissance);

        // When
        LocalDate dateNaissanceAuteur = auteur.getDateNaissance();

        // Then
        assertEquals(dateNaissance, dateNaissanceAuteur);
    }

    @Test
    void setDateNaissance() {
        // Given
        LocalDate dateNaissance = LocalDate.of(1980, 1, 1);

        // When
        auteur.setDateNaissance(dateNaissance);

        // Then
        assertEquals(dateNaissance, auteur.getDateNaissance());
    }

    @Test
    void getDateDeces() {
        // Given
        LocalDate dateDeces = LocalDate.of(2020, 12, 31);
        auteur.setDateDeces(dateDeces);

        // When
        LocalDate dateDecesAuteur = auteur.getDateDeces();

        // Then
        assertEquals(dateDeces, dateDecesAuteur);
    }

    @Test
    void setDateDeces() {
        // Given
        LocalDate dateDeces = LocalDate.of(2020, 12, 31);

        // When
        auteur.setDateDeces(dateDeces);

        // Then
        assertEquals(dateDeces, auteur.getDateDeces());
    }

    @Test
    void getAddedAt() {
        // Given
        LocalDate addedAt = LocalDate.now();
        auteur.setAddedAt(addedAt);

        // When
        LocalDate addedAtAuteur = auteur.getAddedAt();

        // Then
        assertEquals(addedAt, addedAtAuteur);
    }

    @Test
    void setAddedAt() {
        // Given
        LocalDate addedAt = LocalDate.now();

        // When
        auteur.setAddedAt(addedAt);

        // Then
        assertEquals(addedAt, auteur.getAddedAt());
    }

    @Test
    void getUpdatedAt() {
        // Given
        LocalDate updatedAt = LocalDate.now();
        auteur.setUpdatedAt(updatedAt);

        // When
        LocalDate updatedAtAuteur = auteur.getUpdatedAt();

        // Then
        assertEquals(updatedAt, updatedAtAuteur);
    }

    @Test
    void setUpdatedAt() {
        // Given
        LocalDate updatedAt = LocalDate.now();

        // When
        auteur.setUpdatedAt(updatedAt);

        // Then
        assertEquals(updatedAt, auteur.getUpdatedAt());
    }

    @Test
    void getLivres() {
        // Given
        Livre livre1 = new Livre();
        Livre livre2 = new Livre();
        List<Livre> livres = new ArrayList<>();
        livres.add(livre1);
        livres.add(livre2);
        auteur.setLivres(livres);

        // When
        List<Livre> livresAuteur = auteur.getLivres();

        // Then
        assertEquals(livres, livresAuteur);
    }

    @Test
    void setLivres() {
        // Given
        Livre livre1 = new Livre();
        Livre livre2 = new Livre();
        List<Livre> livres = new ArrayList<>();
        livres.add(livre1);
        livres.add(livre2);

        // When
        auteur.setLivres(livres);

        // Then
        assertEquals(livres, auteur.getLivres());
    }
}