package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom;

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    @OneToMany(mappedBy = "categorie",
            cascade ={
                    CascadeType.PERSIST,
                    CascadeType.MERGE

            }
    )
    private List<Livre> livres = new ArrayList<>();

    // Constructeurs, getters et setters
    public void addLivre(Livre livre) {
        livres.add(livre);
        livre.setCategorie(this);
    }

    public void removeLivre(Livre livre) {
        livres.remove(livre);
        livre.setCategorie(null);
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