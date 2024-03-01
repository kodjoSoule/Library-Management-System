package com.lms.librarymanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import java.util.Date;


@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Exemplaire exemplaireEmprunte;
    @ManyToOne
    private DBUser adherentEmprunteur;

    private Date dateEmprunt;
    private Date dateRetourPrevue;
    private Date dateRetourEffectif;

    // Getters et Setters
}