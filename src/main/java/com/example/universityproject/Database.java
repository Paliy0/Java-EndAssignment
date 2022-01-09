package com.example.universityproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final List<User> users = new ArrayList<>();
    private final ObservableList<Movie> movieList = FXCollections.observableArrayList();
    private Room room1 = new Room(1, 200);
    private Room room2 = new Room(2, 100);

    public Database() {
        users.add(new User("admin", "admin123!", true));
        users.add(new User("john", "john123!", false));


        Movie movie1 = new Movie("No time to lie", 2, 12.00);
        Movie movie2 = new Movie("The Adams family 19", 2, 9.00);
        movieList.add(movie1);
        movieList.add(movie2);

        room1.addShowing(movie1, LocalDateTime.of(2021, Month.OCTOBER, 9, 20, 0));
        room1.addShowing(movie2, LocalDateTime.of(2021, Month.OCTOBER, 10, 22, 30));

        room2.addShowing(movie1, LocalDateTime.of(2021, Month.OCTOBER, 9, 20, 0));
        room2.addShowing(movie2, LocalDateTime.of(2021, Month.OCTOBER, 10, 22, 30));
    }

    public List<User> getUsers() {
        return users;
    }

    public Room getRoom1() { return room1; }

    public Room getRoom2() {
        return room2;
    }

    public ObservableList<Movie> getMovieList() { return movieList; }

    public void addMovie(Movie movie){
        movieList.add(movie);
    }

    boolean PasswordValidation(String password) {
        boolean charPresent = false;
        boolean numPresent = false;
        boolean sCharPresent = false;
        for(char c : password.toCharArray()) {
            if(Character.isLetter(c)){
                charPresent = true;
            }
            else if (Character.isDigit(c)) {
                numPresent = true;
            }
            else {
                sCharPresent = true;
            }
        }
        return password.length() > 7 && charPresent && numPresent && sCharPresent;
    }
    public User validateUser(String username, String password) {
        for (User user : getUsers()) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}