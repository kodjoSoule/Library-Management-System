package com.lms.librarymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int nbPages;

    private LocalDate datePublication;

    private String editeur;
    private String langue;

    @JsonBackReference
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name="auteur")

    private Auteur auteur;


    @JsonBackReference
    @OneToMany(
            mappedBy = "livre", cascade = CascadeType.ALL, orphanRemoval = true)

    @JsonIgnore
    private List<Exemplaire> exemplaires = new ArrayList<>() ;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "added_by")
    private Admin addedBy;

    public Categorie getCategorie() {
        return categorie;
    }

    //image du livre, par le model Image
    @JsonBackReference
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private ImageData image;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;


    public void setExemplaires(List<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    public String getNomCategorie() {
        return categorie != null ? categorie.getNom() : null;
    }
    public String getImgUrl2() {
        return image != null ? image.getFilePath() : null;
    }
    public String getImageUrl() {
        return image != null ? image.getFilePath() : null;
    }

//    public void setExemplaires(List<Exemplaire> exemplaires) {
//        this.exemplaires = exemplaires;
//    }

    public Admin getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Admin addedBy) {
        this.addedBy = addedBy;
    }


    // Constructeur

    // Méthode pour obtenir le nombre d'exemplaires
    public int getNombreExemplaires() {
        return exemplaires.size();
    }

    // Méthode pour vérifier la disponibilité du livre
    public boolean estDisponible() {
        return exemplaires.stream().anyMatch(Exemplaire::estDisponible);
    }

    // Méthode pour ajouter un exemplaire associé à ce livre
    public void addExemplaire(Exemplaire exemplaire) {
        this.exemplaires.add(exemplaire);
        exemplaire.setLivre(this);
    }
  @Override
  public String toString() {
        return "Livre{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                ", auteur=" + auteur +
                ", exemplaires=" + exemplaires +
                ", addedBy=" + addedBy +
                '}';
  }


    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
}
