package com.lms.librarymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Livre livre;

    @ManyToOne
    private Utilisateur utilisateur ;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    private boolean retourne;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffectif;


    @Transient
    @JsonIgnore
    private int adminId ;
    @Transient
    @JsonIgnore
    private int utilisateurId ;
    @Transient
    @JsonIgnore
    private int livreId ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livre getExemplaire() {
        return livre ;
    }

    public void setExemplaire(Livre livreEmprunter) {
        livre = livreEmprunter;
    }
    //constructeur
    public Emprunt() {
        this.retourne = false;
        this.dateEmprunt = LocalDate.now();
        this.dateRetourPrevue = LocalDate.now().plusDays(14);
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