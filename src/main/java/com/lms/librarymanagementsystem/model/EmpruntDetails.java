package com.lms.librarymanagementsystem.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpruntDetails {
    private long empruntId;
    private long livreId ;
    private long adherentId ;
    private long exemplaireId ;
    private long adminId ;
    private long utilisateurId ;
    private long categorieId ;
    private boolean prolonge;
    private boolean retourne;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffectif;
    private String nomAdherent;
    private String prenomAdherent;
    private String  auteurLivre ;
    private Emprunt emprunt;
    private String isbnLivre ;
    private String titreLivre ;
    private String editeurLivre ;
    private String categorieLivre ;
}