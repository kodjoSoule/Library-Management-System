package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Image {
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
    private byte[] image;


    @OneToOne(fetch = FetchType.LAZY,
            cascade ={
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "image"
    )
    @JoinColumn(name = "livre_id")
    private Livre livre;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
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
}
