package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String raison;
    private  int niveau;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt ;
    @OneToOne
    private Utilisateur utilisateur;

}