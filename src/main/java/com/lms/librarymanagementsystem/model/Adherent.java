package com.lms.librarymanagementsystem.model;

public class Adherent extends DBUser{
    public Adherent() {
        super();
    }
    public Adherent(String username, String password) {
        super(username, password, "ADHERENT");
    }
}
