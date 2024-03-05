package com.lms.librarymanagementsystem.model;

public class Admin extends DBUser {
    public Admin() {
        super();
    }
    public Admin(String username, String password) {
        super(username, password, "ADMIN");
    }

}
