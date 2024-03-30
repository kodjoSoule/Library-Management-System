package com.lms.librarymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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


    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Message> messages;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private Penalite penalite;

    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Utilisateur() {

    }

    public Utilisateur(String mohamed, String ben, String mail, String admin, String adminPassword) {
        this.firstName = mohamed;
        this.lastName = ben;
        this.email = mail;
        this.username = admin;
        this.password = adminPassword;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Utilisateur orElseThrow(Object o) {
    return null;
    }

    public void setMessage(Message message) {

    }
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public void setNom(String nom) {
        this.lastName = nom;
    }

    public void setPrenom(String firstName) {
        this.firstName = firstName;

    }
}