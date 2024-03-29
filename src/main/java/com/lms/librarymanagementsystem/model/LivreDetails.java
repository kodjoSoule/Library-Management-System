package com.lms.librarymanagementsystem.model;

import com.lms.librarymanagementsystem.service.LivreService;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class LivreDetails {
    private Livre livre;
    private Admin admin;
    private Auteur auteurLivre;
    private ImageData imageData;
    private Categorie categorieLivre;
    private int nbExemplaires = 1;

    private int livreId ;
    private String isbn;
    private String titre;
    private String description;
    private int nbPages;
    private LocalDate datePublication;
    private String editeur;
    private String langue;
    private String auteur; // ID de l'auteur du livre
    private String newAuteur; // Nouvel auteur du livre
    private String newCategorie; // Nouvelle catégorie du livre
    private Long adminID;

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    private String categorie; // ID de la catégorie du livre
    private MultipartFile imageFile; // Fichier image du livre


    // Getters and Setters
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

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getNewAuteur() {
        return newAuteur;
    }

    public void setNewAuteur(String newAuteur) {
        this.newAuteur = newAuteur;
    }

    public String getNewCategorie() {
        return newCategorie;
    }

    public void setNewCategorie(String newCategorie) {
        this.newCategorie = newCategorie;
    }

    @Override
    public String toString() {
        return "LivreDetails{" +
                "isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", nbPages=" + nbPages +
                ", datePublication=" + datePublication +
                ", editeur='" + editeur + '\'' +
                ", langue='" + langue + '\'' +
                ", auteurId=" + auteur +
                ", categorieId=" + categorie +
                ", imageFile=" + imageFile +
                ", newAuteur='" + newAuteur + '\'' +
                ", newCategorie='" + newCategorie + '\'' +
                '}';
    }


    public void setLivre(Livre livre) {

    }

    public void setLivreId(Long id) {

    }
}