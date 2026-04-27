package com.example.realestateapp;

import java.io.Serializable;

public class Property implements Serializable {
    private String name;
    private String location;
    private String price;
    private String imageUri;
    private String description;
    private String propertyType;
    private String lookingTo;
    private String bhk;
    private String floors;

    public Property(String name, String location, String price, String imageUri, String description, 
                    String propertyType, String lookingTo, String bhk, String floors) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.imageUri = imageUri;
        this.description = description;
        this.propertyType = propertyType;
        this.lookingTo = lookingTo;
        this.bhk = bhk;
        this.floors = floors;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getPrice() { return price; }
    public String getImageUri() { return imageUri; }
    public String getDescription() { return description; }
    public String getPropertyType() { return propertyType; }
    public String getLookingTo() { return lookingTo; }
    public String getBhk() { return bhk; }
    public String getFloors() { return floors; }
}