package com.example.universityproject;

import java.time.LocalDateTime;

public class Movie {
    public String title;
    public int duration;
    public double price;

    public Movie(String title, int duration, double price) {
        this.title = title;
        this.duration = duration;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getEnd(LocalDateTime start){
        return start.plusHours(duration);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return getTitle();
    }
}
