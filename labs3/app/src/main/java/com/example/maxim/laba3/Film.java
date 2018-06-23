package com.example.maxim.laba3;

/**
 * Created by maxim on 10.04.2018.
 */

public class Film {
    private int imageId;
    private String name;
    private String country;
    private double rating;
    private String info;

    public Film(int imageId, String name, String country, double rating, String info) {
        this.imageId = imageId;
        this.name = name;
        this.country = country;
        this.rating = rating;
        this.info = info;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
