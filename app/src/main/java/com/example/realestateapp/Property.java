package com.example.realestateapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Property implements Serializable {
    private String name;
    private String location;
    private String price;
    private String imageUri;
    private String videoUri;
    private List<String> additionalImages;
    private String description;
    private String propertyType;
    private String lookingTo;
    private String bhk;
    private String floors;
    private String sqft;
    private String dealerName;
    private String listingType;
    
    // Detailed fields from attachments
    private String baths;
    private String superArea;
    private String pricePerSqft;
    private String propertyAge;
    private String furnishingStatus;
    private String ownership;
    private String overlooking;
    private String facing;
    private String roadWidth;
    private String waterSource;
    private String flooring;
    private String wheelchairFriendly;
    private String dealerDaysAgo;
    private String priceInfo;
    private List<String> facilities;
    private List<String> nearbyPlaces;
    private List<String> essentialFacilities;
    private List<String> furnishings;

    public Property(String name, String location, String price, String imageUri, String description, 
                    String propertyType, String lookingTo, String bhk, String floors, String sqft, 
                    String dealerName, String listingType) {
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
        this.facilities = new ArrayList<>();
        this.nearbyPlaces = new ArrayList<>();
        this.essentialFacilities = new ArrayList<>();
        this.furnishings = new ArrayList<>();
        
        // Defaults
        this.baths = "2 Baths";
        this.superArea = sqft != null ? sqft : "1200 sqft";
        this.pricePerSqft = "₹ 5,000 /sqft";
        this.propertyAge = "5-10 Years";
        this.furnishingStatus = "Semifurnished";
        this.ownership = "Freehold";
        this.overlooking = "Main Road, Garden";
        this.facing = "East";
        this.roadWidth = "30 ft";
        this.waterSource = "24*7 Water";
        this.flooring = "Marble";
        this.wheelchairFriendly = "Yes";
        this.dealerDaysAgo = "5 days ago";
        this.priceInfo = "Negotiable, 1% brokerage";
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getPrice() { return price; }
    public String getImageUri() { return imageUri; }
    public void setImageUri(String imageUri) { this.imageUri = imageUri; }
    public String getVideoUri() { return videoUri; }
    public void setVideoUri(String videoUri) { this.videoUri = videoUri; }
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
    public String getBaths() { return baths; }
    public void setBaths(String baths) { this.baths = baths; }
    public String getSuperArea() { return superArea; }
    public void setSuperArea(String superArea) { this.superArea = superArea; }
    public String getPricePerSqft() { return pricePerSqft; }
    public void setPricePerSqft(String pricePerSqft) { this.pricePerSqft = pricePerSqft; }
    public String getPropertyAge() { return propertyAge; }
    public void setPropertyAge(String propertyAge) { this.propertyAge = propertyAge; }
    public String getFurnishingStatus() { return furnishingStatus; }
    public void setFurnishingStatus(String furnishingStatus) { this.furnishingStatus = furnishingStatus; }
    public String getOwnership() { return ownership; }
    public void setOwnership(String ownership) { this.ownership = ownership; }
    public String getOverlooking() { return overlooking; }
    public void setOverlooking(String overlooking) { this.overlooking = overlooking; }
    public String getFacing() { return facing; }
    public void setFacing(String facing) { this.facing = facing; }
    public String getRoadWidth() { return roadWidth; }
    public void setRoadWidth(String roadWidth) { this.roadWidth = roadWidth; }
    public String getWaterSource() { return waterSource; }
    public void setWaterSource(String waterSource) { this.waterSource = waterSource; }
    public String getFlooring() { return flooring; }
    public void setFlooring(String flooring) { this.flooring = flooring; }
    public String getWheelchairFriendly() { return wheelchairFriendly; }
    public void setWheelchairFriendly(String wheelchairFriendly) { this.wheelchairFriendly = wheelchairFriendly; }
    public String getDealerDaysAgo() { return dealerDaysAgo; }
    public void setDealerDaysAgo(String dealerDaysAgo) { this.dealerDaysAgo = dealerDaysAgo; }
    public String getPriceInfo() { return priceInfo; }
    public void setPriceInfo(String priceInfo) { this.priceInfo = priceInfo; }
    public List<String> getFacilities() { return facilities; }
    public void setFacilities(List<String> facilities) { this.facilities = facilities; }
    public List<String> getNearbyPlaces() { return nearbyPlaces; }
    public void setNearbyPlaces(List<String> nearbyPlaces) { this.nearbyPlaces = nearbyPlaces; }
    public List<String> getEssentialFacilities() { return essentialFacilities; }
    public void setEssentialFacilities(List<String> essentialFacilities) { this.essentialFacilities = essentialFacilities; }
    public List<String> getFurnishings() { return furnishings; }
    public void setFurnishings(List<String> furnishings) { this.furnishings = furnishings; }
}