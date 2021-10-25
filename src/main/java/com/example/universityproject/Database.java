package com.example.universityproject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<User> users = new ArrayList<>();
    private List<Movie> movies1 = new ArrayList<>();
    private List<Movie> movies2 = new ArrayList<>();

    public Database() {
        users.add(new User("admin", "admin123!", true));
        users.add(new User("john", "john123!", false));

        movies1.add(new Movie("No time to lie", 200, 12.00, LocalDateTime.of(2021, Month.OCTOBER, 9, 20, 0), LocalDateTime.of(2021, Month.OCTOBER, 9, 22, 5)));
        movies1.add(new Movie("The Adams family 19", 200, 9.00, LocalDateTime.of(2021, Month.OCTOBER, 10, 22, 30), LocalDateTime.of(2021, Month.OCTOBER, 10, 0, 2)));

        movies2.add(new Movie("No time to lie", 100, 12.00, LocalDateTime.of(2021, Month.OCTOBER, 9, 20, 0), LocalDateTime.of(2021, Month.OCTOBER, 9, 22, 5)));
        movies2.add(new Movie("The Adams family 19", 100, 9.00, LocalDateTime.of(2021, Month.OCTOBER, 10, 22, 30), LocalDateTime.of(2021, Month.OCTOBER, 10, 0, 2)));
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Movie> getRoom1() {
        return movies1;
    }

    public List<Movie> getRoom2() {
        return movies2;
    }
}