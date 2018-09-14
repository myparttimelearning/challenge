package com.rumodigi.fanduelmobilechallenge.domain.models;

public class Player {

    private String id;
    private String firstName;
    private String lastName;
    private double fppg;
    private String imageUrl;

    public Player(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getFppg() {
        return fppg;
    }

    public void setFppg(double fppg) {
        this.fppg = fppg;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
