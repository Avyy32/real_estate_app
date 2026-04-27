package com.example.realestateapp;

import java.util.ArrayList;
import java.util.Arrays;
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

        List<String> poolImages = Arrays.asList(
            "https://images.unsplash.com/photo-1580587771525-78b9dba3b914",
            "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab",
            "https://images.unsplash.com/photo-1497366216548-37526070297c",
            "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267",
            "https://images.unsplash.com/photo-1512917774080-9991f1c4c750",
            "https://images.unsplash.com/photo-1560518883-ce09059eeffa"
        );

        String[] dealers = {"Goyal Associates", "Mukul Gupta", "Everonn Ventures", "Rahul Sharma", "Premium Realty"};

        // 100 Properties (20 each for Noida, Delhi, Mumbai, Gurgaon, Others)
        String[] cities = {"Noida", "Delhi", "Mumbai", "Gurgaon", "Sirsa"};
        for (String city : cities) {
            for (int i = 1; i <= 20; i++) {
                String type = (i % 4 == 0) ? "Commercial" : "Apartment";
                String looking = (i % 2 == 0) ? "Sell" : "Rent / Lease";
                String badge = (looking.equals("Sell")) ? "Resale" : "Rent";
                String price = (looking.equals("Sell")) ? "₹ " + (1 + i/10.0) + " Cr" : "₹ " + (20 + i) + ",000/mo";
                
                Property p = new Property(city + " Prime Tower " + i, "Sector " + (100 + i) + ", " + city, price, 
                    poolImages.get(i % poolImages.size()), longDesc, type, looking, "3 BHK", "15 Floors", 
                    (1000 + i*50) + " sqft", dealers[i % 5], badge);
                
                // Add 5 images to each
                p.setAdditionalImages(poolImages);
                properties.add(p);
            }
        }
    }
}