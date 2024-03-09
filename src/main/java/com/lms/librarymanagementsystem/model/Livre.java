package com.lms.librarymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String titre;
    //autre propriete du livre
    //Description du livre
    private String description;
    //Nombre de pages
    private int nbPages;
    //Date de publication
    private LocalDate datePublication;
    //Editeur
    private String editeur;
    //Langue
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
    //utisaeur qui a ajoute le livre
    @JsonBackReference
    @ManyToOne
    @JsonIgnore
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
    @JsonIgnore
    private ImageLivre image;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public void setExemplaires(List<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    public ImageLivre getImage() {
        return image;
    }

    public void setImage(ImageLivre imageLivre) {
        this.image = imageLivre;
    }

    public List<Exemplaire> getExemplaires() {
        return exemplaires;
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

}