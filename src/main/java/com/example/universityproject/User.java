package com.example.universityproject;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;
    private boolean admin;

    public User(String userName, String password, boolean admin) {
        this.userName = userName;
        this.password = password;
        this.admin = admin;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }
}
