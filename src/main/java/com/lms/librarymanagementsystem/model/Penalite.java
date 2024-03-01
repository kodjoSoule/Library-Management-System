package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;

@Entity
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Emprunt emprunt;

    private double montant;
    private String raison;
    private boolean validee;

    // Getters and setters
}