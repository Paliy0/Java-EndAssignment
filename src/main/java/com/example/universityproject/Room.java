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

    public void addListener(ListChangeListener<Showing> listener) {
        this.showings.addListener(listener);
    }

    public void addShowing(Movie movie, LocalDateTime start) {
        showings.add(new Showing(movie, totalSeats, start));
    }

    public ObservableList<Showing> getShowings (){
        return this.showings;
    }
}
