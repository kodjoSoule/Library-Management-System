package com.lms.librarymanagementsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EmpruntDetailsTest {

    private EmpruntDetails empruntDetails;

    @BeforeEach
    public void setUp() {
        empruntDetails = new EmpruntDetails();
    }

    @Test
    public void testGetId() {
        empruntDetails.setEmpruntId(1L);
        assertEquals(1, empruntDetails.getEmpruntId()   );
    }

    @Test
    public void testSetId() {
        empruntDetails.setEmpruntId(1L);
        assertEquals(1, empruntDetails.getEmpruntId()   );
    }

    @Test
    public void testGetDateEmprunt() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        empruntDetails.setDateEmprunt(date);
        assertEquals(date, empruntDetails.getDateEmprunt());
    }

    @Test
    public void testSetDateEmprunt() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        empruntDetails.setDateEmprunt(date);
        assertEquals(date, empruntDetails.getDateEmprunt());
    }


}