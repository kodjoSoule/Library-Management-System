package com.lms.librarymanagementsystem.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String nationalite;
    private String biographie;
    private LocalDate dateNaissance;
    private LocalDate dateDeces;
    private LocalDate addedAt ;
    private LocalDate updatedAt;
    //liste des livres ecrits par l'auteur

    @JsonManagedReference
    @OneToMany(
            mappedBy = "auteur",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Livre> livres = new ArrayList<>();

    //add image
    @OneToOne(fetch = FetchType.LAZY,
            cascade =CascadeType.ALL
    )
    private ImageAuteur image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        //en Majuscule
        this.nom = this.nom.toUpperCase();
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom.toUpperCase();
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public LocalDate getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(LocalDate dateDeces) {
        this.dateDeces = dateDeces;
    }

    public LocalDate getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDate addedAt) {
        this.addedAt = addedAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }
}