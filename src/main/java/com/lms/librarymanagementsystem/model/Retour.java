package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Retour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Emprunt emprunt;

    private LocalDate dateRetourEffectif;
    private boolean retard;
    private boolean penaliteAppliquee;

    // Getters and setters
}