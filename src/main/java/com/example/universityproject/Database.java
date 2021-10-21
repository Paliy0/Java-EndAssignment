package com.example.universityproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<User> users = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();

    public Database() {
        users.add(new User("admin", "admin123!", true));
        users.add(new User("john", "john123!", false));

        movies.add(new Movie("No time to lie", 200, 12.00, new SimpleDateFormat("09-10-2021 20:00"), new SimpleDateFormat("09-10-2021 22:05")));
        movies.add(new Movie("The Adams family 19", 200, 9.00, new SimpleDateFormat("09-10-2021 22:30"), new SimpleDateFormat("09-10-2021 00:02")));
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}