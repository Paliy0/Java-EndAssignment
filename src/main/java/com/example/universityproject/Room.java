package com.example.universityproject;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.List;

public class Room {
    private int id;
    private int totalSeats;
    private ObservableList<Showing> showings;

    public Room(int id, int totalSeats) {
        this.id = id;
        this.totalSeats = totalSeats;
        this.showings = FXCollections.observableArrayList();
    }

    public void addShowing(Movie movie, LocalDateTime start) {
        showings.add(new Showing(movie, totalSeats, start));
    }

    public int getId(){return id;}

    public ObservableList<Showing> getShowings (){
        return this.showings;
    }

    public int getTotalSeats(){
        return totalSeats;
    }

    @Override
    public String toString(){
        return "Room " + id;
    }
}
