package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
public class ImageLivre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //
    private String title ;
    // Ceci stocke le nom du fichier pour référence
    private  String fileName;
    //addedAt
    private LocalDate addedAt;
    //updateAt
    private LocalDate updateAt;


    @OneToOne(fetch = FetchType.LAZY,
            cascade ={
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "image"
    )
    @JoinColumn(name = "livre_id")
    private Livre livre;
    private String uri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public void setUri(String string) {
        this.uri = string;
    }

    public void setName(String s) {
        this.fileName = s;
    }

    public String getName() {
        return fileName;
    }
}
