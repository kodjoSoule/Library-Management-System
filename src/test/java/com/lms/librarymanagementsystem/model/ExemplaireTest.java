package com.lms.librarymanagementsystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExemplaireTest {
    private Exemplaire exemplaire;

    @BeforeEach
    void setUp() {
        exemplaire = new Exemplaire();
    }

    @Test
    void estDisponible() {
        // Given
        exemplaire.setStatus("Disponible");

        // When
        boolean disponible = exemplaire.estDisponible();

        // Then
        assertTrue(disponible);
    }

    @Test
    void ajouterExemplaires() {
        // Given
        int nombreExemplaires = 5;
        Livre livre = new Livre();

        // When
        Exemplaire.ajouterExemplaires(livre, nombreExemplaires);
        int nombreExemplairesLivre = livre.getExemplaires().size();

        // Then
        assertEquals(nombreExemplaires, nombreExemplairesLivre);
    }

    @Test
    void getId() {
        // Given
        Long id = 1L;
        exemplaire.setId(id);

        // When
        Long idExemplaire = exemplaire.getId();

        // Then
        assertEquals(id, idExemplaire);
    }

    @Test
    void setId() {
        // Given
        Long id = 2L;

        // When
        exemplaire.setId(id);

        // Then
        assertEquals(id, exemplaire.getId());
    }

    @Test
    void getLivre() {
        // Given
        Livre livre = new Livre();
        exemplaire.setLivre(livre);

        // When
        Livre livreExemplaire = exemplaire.getLivre();

        // Then
        assertEquals(livre, livreExemplaire);
    }

    @Test
    void setLivre() {
        // Given
        Livre livre = new Livre();

        // When
        exemplaire.setLivre(livre);

        // Then
        assertEquals(livre, exemplaire.getLivre());
    }

    @Test
    void getStatus() {
        // Given
        String status = "Disponible";
        exemplaire.setStatus(status);

        // When
        String statusExemplaire = exemplaire.getStatus();

        // Then
        assertEquals(status, statusExemplaire);
    }

    @Test
    void setStatus() {
        // Given
        String status = "Emprunté";

        // When
        exemplaire.setStatus(status);

        // Then
        assertEquals(status, exemplaire.getStatus());
    }

    @Test
    void getDateDeRetourPrevue() {
        // Given
        LocalDate dateRetourPrevue = LocalDate.now().plusDays(7);
        exemplaire.setDateDeRetourPrevue(dateRetourPrevue);

        // When
        LocalDate dateRetourPrevueExemplaire = exemplaire.getDateDeRetourPrevue();

        // Then
        assertEquals(dateRetourPrevue, dateRetourPrevueExemplaire);
    }

    @Test
    void setDateDeRetourPrevue() {
        // Given
        LocalDate dateRetourPrevue = LocalDate.now().plusDays(14);

        // When
        exemplaire.setDateDeRetourPrevue(dateRetourPrevue);

        // Then
        assertEquals(dateRetourPrevue, exemplaire.getDateDeRetourPrevue());
    }
}