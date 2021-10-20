package com.example.universityproject;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {
    private String title;
    private Integer seats;
    private Integer price;
    private Date startDate;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private Date endDate;

    public Movie(String title, Integer seats, Integer price, Date startDate, Date endDate) {
        this.title = title;
        this.seats = seats;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
