package com.lms.librarymanagementsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class PenaliteTest {

    private Penalite penalite;

    @BeforeEach
    public void setUp() {
        penalite = new Penalite();
    }

    @Test
    public void testGetId() {
        penalite.setId(1L);
        assertEquals(1, penalite.getId());
    }

    @Test
    public void testSetId() {
        penalite.setId(1L);
        assertEquals(1, penalite.getId());
    }

    @Test
    public void testGetDate() {
        penalite.setDate("2022-01-01");
        assertEquals("2022-01-01", penalite.getDate());
    }

    @Test
    public void testSetDate() {
        penalite.setDate("2022-01-01");
        assertEquals("2022-01-01", penalite.getDate());
    }

    @Test
    public void testGetRaison() {
        penalite.setRaison("Test raison");
        assertEquals("Test raison", penalite.getRaison());
    }

    @Test
    public void testSetRaison() {
        penalite.setRaison("Test raison");
        assertEquals("Test raison", penalite.getRaison());
    }

    @Test
    public void testGetNiveau() {
        penalite.setNiveau(1);
        assertEquals(1, penalite.getNiveau());
    }

    @Test
    public void testSetNiveau() {
        penalite.setNiveau(1);
        assertEquals(1, penalite.getNiveau());
    }

    @Test
    public void testGetCreatedAt() {
        Date date = new Date();
        penalite.setCreatedAt(date);
        assertEquals(date, penalite.getCreatedAt());
    }

    @Test
    public void testSetCreatedAt() {
        Date date = new Date();
        penalite.setCreatedAt(date);
        assertEquals(date, penalite.getCreatedAt());
    }

}