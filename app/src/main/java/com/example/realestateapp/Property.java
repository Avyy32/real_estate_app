package com.example.realestateapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Property implements Serializable {
    private String name;
    private String location;
    private String price;
    private String imageUri; // Main thumbnail
    private List<String> additionalImages;
    private String description;
    private String propertyType;
    private String lookingTo;
    private String bhk;
    private String floors;
    private String sqft;
    private String dealerName;
    private String listingType; // "Resale" or "New"

    public Property(String name, String location, String price, String imageUri, String description, 
                    String propertyType, String lookingTo, String bhk, String floors) {
        this(name, location, price, imageUri, description, propertyType, lookingTo, bhk, floors, null, "Goyal Associates", "Resale");
    }

    public Property(String name, String location, String price, String imageUri, String description, 
                    String propertyType, String lookingTo, String bhk, String floors, String sqft, String dealerName) {
        this(name, location, price, imageUri, description, propertyType, lookingTo, bhk, floors, sqft, dealerName, "Resale");
    }

    public Property(String name, String location, String price, String imageUri, String description, 
                    String propertyType, String lookingTo, String bhk, String floors, String sqft, String dealerName, String listingType) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.imageUri = imageUri;
        this.description = description;
        this.propertyType = propertyType;
        this.lookingTo = lookingTo;
        this.bhk = bhk;
        this.floors = floors;
        this.sqft = sqft;
        this.dealerName = dealerName;
        this.listingType = listingType;
        this.additionalImages = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getPrice() { return price; }
    public String getImageUri() { return imageUri; }
    public List<String> getAdditionalImages() { return additionalImages; }
    public void setAdditionalImages(List<String> additionalImages) { this.additionalImages = additionalImages; }
    public String getDescription() { return description; }
    public String getPropertyType() { return propertyType; }
    public String getLookingTo() { return lookingTo; }
    public String getBhk() { return bhk; }
    public String getFloors() { return floors; }
    public String getSqft() { return sqft; }
    public String getDealerName() { return dealerName; }
    public String getListingType() { return listingType; }
}