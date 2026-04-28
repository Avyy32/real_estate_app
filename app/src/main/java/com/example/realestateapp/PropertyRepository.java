package com.example.realestateapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PropertyRepository {
    private static PropertyRepository instance;
    private final List<Property> properties;
    
    public String draftLookingTo;
    public String draftPropertyType;
    public String draftContact;
    public String draftFloors;
    public String draftPrice;
    public String draftAddress;
    public String draftBHK;

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
        String longDesc = "This exceptional property offers a rare combination of luxury, comfort, and convenience. Nestled in a prime location, it features state-of-the-art architecture and high-end finishes throughout. The spacious living areas are bathed in natural light, creating a warm and inviting atmosphere.";

        String[] dealers = {"Chirag Goyal", "Mukul Gupta", "Rahul Sharma", "Ankit Verma", "Priya Singh", "Sonia Malhotra"};
        String[] cities = {"Noida", "Delhi", "Mumbai", "Gurgaon", "Sirsa"};
        String[] facing = {"North", "South", "East", "West", "North-East", "South-East"};
        String[] flooring = {"Marble", "Vitrified Tiles", "Wooden", "Granite"};
        String[] ownership = {"Freehold", "Leasehold", "Power of Attorney"};

        // High-quality Real Estate Image Sets (Thematic)
        String[][] imagePools = {
            // Set 1: Luxury Villas
            {"https://images.unsplash.com/photo-1580587771525-78b9dba3b914", "https://images.unsplash.com/photo-1600585154340-be6161a56a0c", "https://images.unsplash.com/photo-1600596542815-ffad4c1539a9", "https://images.unsplash.com/photo-1600607687940-47a04023998b", "https://images.unsplash.com/photo-1600566753190-17f0bb2a6c3e"},
            // Set 2: Modern Apartments
            {"https://images.unsplash.com/photo-1522708323590-d24dbb6b0267", "https://images.unsplash.com/photo-1502672260266-1c1ef2d93688", "https://images.unsplash.com/photo-1484154218962-a197022b5858", "https://images.unsplash.com/photo-1502005229762-cf1b2da7c5d6", "https://images.unsplash.com/photo-1493809842364-78817add7ffb"},
            // Set 3: Commercial/Offices
            {"https://images.unsplash.com/photo-1486406146926-c627a92ad1ab", "https://images.unsplash.com/photo-1497366216548-37526070297c", "https://images.unsplash.com/photo-1497215728101-856f4ea42174", "https://images.unsplash.com/photo-1449156003106-2199f2163d51", "https://images.unsplash.com/photo-1516156008625-3a9d6067fab5"},
            // Set 4: Houses
            {"https://images.unsplash.com/photo-1512917774080-9991f1c4c750", "https://images.unsplash.com/photo-1513584684374-8bdb74838a0f", "https://images.unsplash.com/photo-1472224317457-5f96bc92017c", "https://images.unsplash.com/photo-1430285561322-7808604715df", "https://images.unsplash.com/photo-1518780664697-55e3ad937233"},
            // Set 5: Penthouses/Modern
            {"https://images.unsplash.com/photo-1515263487990-61b07816b324", "https://images.unsplash.com/photo-1512915920307-446f1ba436f6", "https://images.unsplash.com/photo-1531971589569-0d9370cbe1e5", "https://images.unsplash.com/photo-1554995207-c18c203602cb", "https://images.unsplash.com/photo-1560518883-ce09059eeffa"}
        };
        
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            String city = cities[i / 20];
            int cityIdx = i % 20 + 1;
            
            String type = (i % 4 == 0) ? "Commercial" : (i % 3 == 0 ? "Independent House" : "Apartment");
            String looking = (i % 2 == 0) ? "Sell" : "Rent / Lease";
            String badge = (looking.equals("Sell")) ? "Resale" : "Rent";
            
            int priceVal = 50 + random.nextInt(500);
            String price = looking.equals("Sell") ? "₹ " + (priceVal/100.0) + " Cr" : "₹ " + (15000 + random.nextInt(50000)) + "/mo";
            
            // Pick a thematic image pool based on type
            int poolIdx = 0;
            if (type.equals("Commercial")) poolIdx = 2;
            else if (type.equals("Independent House")) poolIdx = 3;
            else if (i % 2 == 0) poolIdx = 1;
            else poolIdx = 4;
            
            String[] selectedPool = imagePools[poolIdx];
            // Main image is the first in the pool (but scrambled slightly for variety)
            String mainImage = selectedPool[i % 5];
            
            Property p = new Property(city + " Prime Tower " + cityIdx, "Sector " + (50 + i) + ", " + city, price, 
                mainImage, longDesc, type, looking, (2 + random.nextInt(4)) + " BHK", (5 + random.nextInt(20)) + " Floors", 
                (800 + random.nextInt(3000)) + " sqft", dealers[random.nextInt(dealers.length)], badge);
            
            // Additional images from the same pool to ensure stylistic consistency
            List<String> propertyImages = new ArrayList<>(Arrays.asList(selectedPool));
            p.setAdditionalImages(propertyImages);

            // Detailed data
            p.setBaths((2 + random.nextInt(3)) + " Baths");
            p.setSuperArea((1000 + random.nextInt(2000)) + " sq.ft.");
            p.setPricePerSqft("₹ " + (3000 + random.nextInt(10000)) + " /sqft");
            p.setPropertyAge(random.nextInt(15) + " Year Old Property");
            p.setFurnishingStatus(i % 2 == 0 ? "Semifurnished" : "Furnished");
            p.setOwnership(ownership[random.nextInt(ownership.length)]);
            p.setOverlooking(i % 2 == 0 ? "Main Road, Park" : "Garden, Pool");
            p.setFacing(facing[random.nextInt(facing.length)]);
            p.setRoadWidth((20 + random.nextInt(40)) + " ft");
            p.setFlooring(flooring[random.nextInt(flooring.length)]);
            p.setDealerDaysAgo(random.nextInt(30) + " days ago");
            
            p.setEssentialFacilities(Arrays.asList("Reserved Parking", "Security Guard", "2 Covered Parking", "In a Gated Society"));
            p.setFurnishings(Arrays.asList("Fan", "Light", "Wardrobe", "Geyser"));
            p.setNearbyPlaces(Arrays.asList(city + " metro station", city + " Central Mall", "City Hospital"));

            properties.add(p);
        }
    }
}
