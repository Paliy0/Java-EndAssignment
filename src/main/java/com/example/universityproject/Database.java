package com.example.universityproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<User> users = new ArrayList<>();

    public Database() {
        users.add(new User("George", "Washington", LocalDate.of(1732, 2, 22)));
        users.add(new User("John", "Adams", LocalDate.of(1735, 10, 30)));
    }

    public List<User> getUsers() {
        return users;
    }
}