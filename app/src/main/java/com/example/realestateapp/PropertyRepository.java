package com.example.realestateapp;

import java.util.ArrayList;
import java.util.List;

public class PropertyRepository {
    private static PropertyRepository instance;
    private final List<Property> properties;
    
    public String draftLookingTo;
    public String draftPropertyType;
    public String draftContact;
    public String draftFloors;
    public String draftPrice;

    private PropertyRepository() {
        properties = new ArrayList<>();
        addInitialProperties();
    }

    public static synchronized PropertyRepository getInstance() {
        if (instance == null) {
            instance = new PropertyRepository();
        }
        return instance;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property property) {
        properties.add(0, property);
    }

    private void addInitialProperties() {
        String longDesc = "This exceptional property offers a rare combination of luxury, comfort, and convenience...";

        // Noida - Mix of Buy/Rent/Commercial
        properties.add(new Property("Noida Luxury Tower 1", "Sector 101, Noida", "₹ 1.1 Cr", 
            "https://images.unsplash.com/photo-1580587771525-78b9dba3b914", longDesc, "Apartment", "Sell", "3 BHK", "15 Floors", "2,690 sqft", "Goyal Associates"));
        
        properties.add(new Property("Prime Commercial Hub", "Sector 18, Noida", "₹ 2.5 Cr", 
            "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab", longDesc, "Commercial", "Sell", "N/A", "5 Floors", "5,000 sqft", "Mukul Gupta"));

        properties.add(new Property("Modern Office Space", "Sector 62, Noida", "₹ 45,000/mo", 
            "https://images.unsplash.com/photo-1497366216548-37526070297c", longDesc, "Commercial", "Rent / Lease", "N/A", "10 Floors", "1,200 sqft", "Everonn Ventures"));

        properties.add(new Property("Green Valley Flat", "Sector 150, Noida", "₹ 25,000/mo", 
            "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267", longDesc, "Apartment", "Rent / Lease", "2 BHK", "12 Floors", "1,100 sqft", "Rahul Sharma"));

        // Delhi
        properties.add(new Property("South Delhi Villa", "Greater Kailash, Delhi", "₹ 8.5 Cr", 
            "https://images.unsplash.com/photo-1512917774080-9991f1c4c750", longDesc, "House", "Sell", "5 BHK", "3 Floors", "4,500 sqft", "GK Realty"));
            
        properties.add(new Property("CP Commercial Plot", "Connaught Place, Delhi", "₹ 15 Cr", 
            "https://images.unsplash.com/photo-1570129477492-45c003edd2be", longDesc, "Commercial", "Sell", "N/A", "0 Floors", "3,000 sqft", "Delhi Estates"));

        // Gurgaon
        properties.add(new Property("Cyber City Office", "Cyber City, Gurgaon", "₹ 2.0 L/mo", 
            "https://images.unsplash.com/photo-1497215728101-856f4ea42174", longDesc, "Commercial", "Rent / Lease", "N/A", "20 Floors", "3,500 sqft", "Premium Realty"));

        properties.add(new Property("Luxury Penthouse", "Golf Course Road, Gurgaon", "₹ 12 Cr", 
            "https://images.unsplash.com/photo-1502672260266-1c1ef2d93688", longDesc, "Apartment", "Sell", "4 BHK", "25 Floors", "5,500 sqft", "Anil Ambani"));

        // Add 20 more generic Noida properties for search results
        for (int i = 5; i <= 25; i++) {
            String type = (i % 3 == 0) ? "Commercial" : "Apartment";
            String looking = (i % 2 == 0) ? "Sell" : "Rent / Lease";
            String dealer = (i % 2 == 0) ? "Mukul Gupta" : "Goyal Associates";
            
            properties.add(new Property("Property " + i + " Noida", "Sector " + (100 + i) + ", Noida", "₹ " + (1 + i/10.0) + " Cr", 
                "https://images.unsplash.com/photo-1564013799919-ab600027ffc6", longDesc, type, looking, "3 BHK", "15 Floors", "2,000 sqft", dealer));
        }
    }
}