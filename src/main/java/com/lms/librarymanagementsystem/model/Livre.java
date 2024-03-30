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


    private int nbExemplaires;

    private int nbExemplairesEmpruntes;


    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "added_by")
    private  Utilisateur addedBy;
    //private Admin addedBy;

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


    public String getNomCategorie() {
        return categorie != null ? categorie.getNom() : null;
    }
    public String getImgUrl2() {
        return image != null ? image.getFilePath() : null;
    }
    public String getImageUrl() {
        return image != null ? image.getFilePath() : null;
    }
    public boolean estDisponible() {
        return nbExemplaires > 0;
    }
  @Override
  public String toString() {
        return "Livre{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                ", auteur=" + auteur +
                ", nbExemplaires=" + nbExemplaires +
                ", addedBy=" + addedBy +
                '}';
  }
  public int getNombreExemplaireDispinible() {
        return nbExemplaires - nbExemplairesEmpruntes;
  }
}
