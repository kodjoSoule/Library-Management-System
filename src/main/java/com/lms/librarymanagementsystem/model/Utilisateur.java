package com.lms.librarymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    @Transient
    @JsonIgnore
    private String confirmPassword;
    private String role;
    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Utilisateur() {

    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}