package com.lms.librarymanagementsystem.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonManagedReference
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name="livre_id")
    private Livre livre;
    // Statut de l'exemplaire (par exemple, "Disponible", "Emprunté")
    private String status;


    private LocalDate dateDeRetourPrevue;

    // Getters et Setters


    // Méthode pour vérifier la disponibilité de l'exemplaire
    public boolean estDisponible() {
        return "Disponible".equals(status);
    }
    // Méthode pour créer et ajouter un nombre donné d'exemplaires à la liste des exemplaires du livre
    public static void ajouterExemplaires(Livre livre, int nombre) {
        for (int i = 0; i < nombre; i++) {
            Exemplaire exemplaire = new Exemplaire();
            exemplaire.setStatus("Disponible");
            livre.ajouterExemplaire(exemplaire);
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDateDeRetourPrevue() {
        return dateDeRetourPrevue;
    }

    public void setDateDeRetourPrevue(LocalDate dateDeRetourPrevue) {
        this.dateDeRetourPrevue = dateDeRetourPrevue;
    }
}