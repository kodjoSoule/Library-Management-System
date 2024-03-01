package com.lms.librarymanagementsystem.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livre livre;

    private boolean disponible;

    @Temporal(TemporalType.DATE)
    private Date dateDeRetourPrevue;

    // Getters et Setters
}