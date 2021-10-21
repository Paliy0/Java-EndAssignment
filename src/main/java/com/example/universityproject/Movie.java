package com.example.universityproject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie implements Serializable {
    private String title;
    private Integer seats;
    private Double price;
    private SimpleDateFormat startDate;
    private SimpleDateFormat endDate;

    public Movie(String title, int seats, double price, SimpleDateFormat startDate, SimpleDateFormat endDate) {
        this.title = title;
        this.seats = seats;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public SimpleDateFormat getStartDate() {
        return startDate;
    }

    public void setStartDate(SimpleDateFormat startDate) {
        this.startDate = startDate;
    }

    public SimpleDateFormat getEndDate() {
        return endDate;
    }

    public void setEndDate(SimpleDateFormat endDate) {
        this.endDate = endDate;
    }
}
