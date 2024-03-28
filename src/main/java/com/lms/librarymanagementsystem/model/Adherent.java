package com.lms.librarymanagementsystem.model;

import jakarta.persistence.Entity;

@Entity
public class Adherent extends Utilisateur{
    public Adherent() {
        super();
    }
    public Adherent(String username, String password) {
        super(username, password);
    }

}
