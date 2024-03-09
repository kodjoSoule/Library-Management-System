package com.lms.librarymanagementsystem.model;

import jakarta.persistence.Entity;

@Entity
public class Adherent extends DBUser{
    private String nom ;
    private  String prenom ;


    public Adherent() {
        super();
        super.setRole("ADHERENT");
    }
    public Adherent(String username, String password) {

        super(username, password, "ADHERENT");
    }

}
