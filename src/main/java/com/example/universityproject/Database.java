package com.example.universityproject;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<User> users = new ArrayList<>();
    private List<Showing> showingsRoom1 = new ArrayList<>();
    private List<Showing> showingsRoom2 = new ArrayList<>();
    private List<Movie> movieList = new ArrayList<>();


    public Database() {
        users.add(new User("admin", "admin123!", true));
        users.add(new User("john", "john123!", false));


        Movie movie1 = new Movie("No time to lie", 2, 12.00);
        Movie movie2 = new Movie("The Adams family 19", 2, 9.00);
        movieList.add(movie1);
        movieList.add(movie2);

        showingsRoom1.add(new Showing(movie1, 200, LocalDateTime.of(2021, Month.OCTOBER, 9, 20, 0), LocalDateTime.of(2021, Month.OCTOBER, 9, 22, 5)));
        showingsRoom1.add(new Showing(movie2, 200, LocalDateTime.of(2021, Month.OCTOBER, 10, 22, 30), LocalDateTime.of(2021, Month.OCTOBER, 10, 0, 2)));

        showingsRoom2.add(new Showing(movie1, 100, LocalDateTime.of(2021, Month.OCTOBER, 9, 20, 0), LocalDateTime.of(2021, Month.OCTOBER, 9, 22, 5)));
        showingsRoom2.add(new Showing(movie2, 100, LocalDateTime.of(2021, Month.OCTOBER, 10, 22, 30), LocalDateTime.of(2021, Month.OCTOBER, 10, 0, 2)));
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Showing> getRoom1() {
        return showingsRoom1;
    }

    public List<Showing> getRoom2() {
        return showingsRoom2;
    }

    public List<Movie> getMovieList() {return movieList; }

    Boolean PasswordValidation(String password) {
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