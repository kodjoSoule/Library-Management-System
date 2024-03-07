package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ImageAuteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;
    //addedAt
    private LocalDate addedAt;
    //updateAt
    private LocalDate updateAt;


    // Champ pour le contenu de l'image (stockage en base64 par exemple)
    @Lob
    @Column(length = 5242880)
    private byte[] imageData;


    @OneToOne(fetch = FetchType.LAZY,
            cascade ={
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "image"
    )
    @JoinColumn(name = "auteur_id")
    private Auteur auteur;

    // Getters et setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] image) {
        this.imageData = image;
    }


    public LocalDate getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDate addedAt) {
        this.addedAt = addedAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
}
