package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Exemplaire exemplaire;
    @ManyToOne
    private Adherent adherent ;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    private boolean valide;
    // Indique si l'emprunt est validé par l'administrateur

    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffectif;


    // Méthode pour valider l'emprunt par l'administrateur
    public void validerEmprunt(Admin admin) {
        this.valide = true;
        this.admin = admin;
        // Vous pouvez également ajouter d'autres actions liées à la validation
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaireEmprunte) {
        this.exemplaire = exemplaireEmprunte;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public LocalDate getDateRetourEffectif() {
        return dateRetourEffectif;
    }

    public void setDateRetourEffectif(LocalDate dateRetourEffectif) {
        this.dateRetourEffectif = dateRetourEffectif;
    }


}