package com.example.universityproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Showing {
    private Movie movie;
    private int seats;
    private LocalDateTime start;
    private LocalDateTime end;

    public Showing(Movie movie, int seats, LocalDateTime start, LocalDateTime end) {
        this.movie = movie;
        this.seats = seats;
        this.start = start;
        this.end = end;
    }
    public String getMovieTitle() {return movie.getTitle(); }

    public String getMoviePrice() {return String.format("%.2f", movie.price); }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getStart() {
        return start.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public String getEnd() {return end.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")); }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
