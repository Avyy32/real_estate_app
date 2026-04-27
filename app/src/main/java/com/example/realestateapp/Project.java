package com.example.realestateapp;

import java.io.Serializable;

public class Project implements Serializable {
    private String name;
    private String location;
    private String priceRange;
    private String details;

    public Project(String name, String location, String priceRange, String details) {
        this.name = name;
        this.location = location;
        this.priceRange = priceRange;
        this.details = details;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getPriceRange() { return priceRange; }
    public String getDetails() { return details; }
}