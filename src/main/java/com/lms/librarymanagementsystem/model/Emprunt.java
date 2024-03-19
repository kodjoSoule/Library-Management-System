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
    private boolean retourne;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffectif;


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
        exemplaireEmprunte.setStatus("Emprunte");
        this.exemplaire = exemplaireEmprunte;

    }
    //constructeur
    public Emprunt() {
        this.retourne = false;
        this.dateEmprunt = LocalDate.now();
        this.dateRetourPrevue = LocalDate.now().plusDays(14);
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


    public void setExemplaireId(Long id) {

    }

    public void setRetourne(boolean retourne) {
        this.retourne = retourne;
    }
    public boolean getRetourne() {
        return retourne;
    }
}