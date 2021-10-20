package com.example.universityproject;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<User> users = new ArrayList<>();

    public Database() {
        users.add(new User("admin", "admin123!", true));
        users.add(new User("john", "john123!", false));
    }

    public List<User> getUsers() {
        return users;
    }
}