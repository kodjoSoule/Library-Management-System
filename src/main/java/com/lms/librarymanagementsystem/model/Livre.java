package com.lms.librarymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.*;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String titre;

    @JsonBackReference
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name="auteur")
    @JsonIgnore
    private Auteur auteur;

    @JsonBackReference
    @OneToMany(
            mappedBy = "livre", cascade = CascadeType.ALL, orphanRemoval = true)

    @JsonIgnore
    private List<Exemplaire> exemplaires = new ArrayList<>() ;
    //utisaeur qui a ajoute le livre
    @JsonBackReference
    @ManyToOne
    @JsonIgnore
    private DBUser addedBy;
    // Getters et setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public List<Exemplaire> getExemplaires() {
        return exemplaires;
    }

//    public void setExemplaires(List<Exemplaire> exemplaires) {
//        this.exemplaires = exemplaires;
//    }

    public DBUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(DBUser addedBy) {
        this.addedBy = addedBy;
    }


    // Constructeur

    // Méthode pour obtenir le nombre d'exemplaires
    public int getNombreExemplaires() {
        return exemplaires.size();
    }

    // Méthode pour vérifier la disponibilité du livre
    public boolean estDisponible() {
        for (Exemplaire exemplaire : exemplaires) {
            // Si un exemplaire est disponible, le livre est disponible
            if (exemplaire.estDisponible()) {
                return true;
            }

        }
        return false;
    }

    // Méthode pour ajouter un exemplaire associé à ce livre
    public void ajouterExemplaire(Exemplaire exemplaire) {
        if (exemplaires == null) {
            exemplaires = new ArrayList<>();
        }
        exemplaires.add(exemplaire);
        exemplaire.setLivre(this);
    }
    // Méthode pour ajouter un nombre spécifique d'exemplaires associés à ce livre
    public void ajouterExemplaires(int nombreExemplaires) {
        if (exemplaires == null) {
            exemplaires = new ArrayList<>();
        }
        for (int i = 0; i < nombreExemplaires; i++) {
            Exemplaire exemplaire = new Exemplaire();
            exemplaire.setStatus("Disponible");
            exemplaires.add(exemplaire);
            exemplaire.setLivre(this);
        }
  }
}
