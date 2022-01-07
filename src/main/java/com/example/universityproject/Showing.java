package com.example.universityproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Showing {
    private Movie movie;
    private int availableSeats;
    private LocalDateTime start;

    public Showing(Movie movie, int availableSeats, LocalDateTime start) {
        this.movie = movie;
        this.availableSeats = availableSeats;
        this.start = start;
    }

    public String getMovieTitle() {return movie.getTitle(); }

    public String getMoviePrice() {return String.format("%.2f", movie.price); }

    public String getAvailableSeats() {return String.format("%d", availableSeats);}

    public void purchaseSeats(int amount) {
        if (availableSeats < amount){
            throw new IllegalArgumentException("Amount exceeded available seats");
        }
        availableSeats-= amount;
    }

    public String getStart() {return start.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));}

    public String getEnd() {return movie.getEnd(start).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")); }
}
