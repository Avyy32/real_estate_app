package com.example.realestateapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {
    private String name;
    private String location;
    private String priceRange;
    private String unitType;
    private String mainImage;
    private List<String> additionalImages;

    public Project(String name, String location, String priceRange, String unitType) {
        this(name, location, priceRange, unitType, "https://images.unsplash.com/photo-1545324418-cc1a3fa10c00");
    }

    public Project(String name, String location, String priceRange, String unitType, String mainImage) {
        this.name = name;
        this.location = location;
        this.priceRange = priceRange;
        this.unitType = unitType;
        this.mainImage = mainImage;
        this.additionalImages = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getPriceRange() { return priceRange; }
    public String getUnitType() { return unitType; }
    public String getMainImage() { return mainImage; }
    public List<String> getAdditionalImages() { return additionalImages; }
    public void setAdditionalImages(List<String> additionalImages) { this.additionalImages = additionalImages; }
}