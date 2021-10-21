package com.example.universityproject;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<User> users = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();

    public Database() {
        users.add(new User("admin", "admin123!", true));
        users.add(new User("john", "john123!", false));

        movies.add(new Movie("No time to lie", 200, 12.00, LocalDateTime.now(), LocalDateTime.now()));
        movies.add(new Movie("The Adams family 19", 200, 9.00, LocalDateTime.now(), LocalDateTime.now()));
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}