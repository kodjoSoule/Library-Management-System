package com.lms.librarymanagementsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EmpruntTest {

    private Emprunt emprunt;

    @BeforeEach
    public void setUp() {
        emprunt = new Emprunt();
    }

    @Test
    public void testGetId() {
        emprunt.setId(1L);
        assertEquals(1, emprunt.getId());
    }

    @Test
    public void testSetId() {
        emprunt.setId(1L);
        assertEquals(1, emprunt.getId());
    }

    @Test
    public void testGetDateEmprunt() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        emprunt.setDateEmprunt(date);
        assertEquals(date, emprunt.getDateEmprunt());
    }

    @Test
    public void testSetDateEmprunt() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        emprunt.setDateEmprunt(date);
        assertEquals(date, emprunt.getDateEmprunt());
    }

    @Test
    public void testGetDateRetour() {
        LocalDate date = LocalDate.of(2022, 1, 15);
        emprunt.setDateRetourPrevue(date);
        assertEquals(date, emprunt.getDateRetourPrevue());
    }

    @Test
    public void testSetDateRetour() {
        LocalDate date = LocalDate.of(2022, 1, 15);
        emprunt.setDateRetourPrevue(date);
        assertEquals(date, emprunt.getDateRetourPrevue());
    }

    @Test
    public void testGetDateRetourEffectif() {
        LocalDate date = LocalDate.of(2022, 1, 15);
        emprunt.setDateRetourEffectif(date);
        assertEquals(date, emprunt.getDateRetourEffectif());
    }

    @Test
    public void testSetDateRetourEffectif() {
        LocalDate date = LocalDate.of(2022, 1, 15);
        emprunt.setDateRetourEffectif(date);
        assertEquals(date, emprunt.getDateRetourEffectif());
    }
}