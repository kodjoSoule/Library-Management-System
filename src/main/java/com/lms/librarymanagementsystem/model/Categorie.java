package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToOne(mappedBy = "categorie")
    private Categorie genre ;
    public Categorie getGenre() {
        return genre;
    }

    public void setGenre(Categorie genre) {
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }





}