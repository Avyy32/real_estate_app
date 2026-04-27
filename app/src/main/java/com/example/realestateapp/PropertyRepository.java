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
        String longDesc = "This exceptional property offers a rare combination of luxury, comfort, and convenience. Nestled in a prime location, it features state-of-the-art architecture and high-end finishes throughout. The spacious living areas are bathed in natural light, creating a warm and inviting atmosphere. The gourmet kitchen is equipped with top-of-the-line appliances and ample storage space. Each bedroom is designed with relaxation in mind, offering a peaceful retreat from the hustle and bustle of the city. The bathrooms are elegantly appointed with modern fixtures and premium materials. Outside, the property boasts beautifully landscaped gardens, providing a serene environment for outdoor activities and entertaining. With its strategic location, you are just minutes away from major transport hubs, shopping centers, and top-rated schools. This property is not just a place to live; it's a lifestyle statement. Experience the pinnacle of modern living with this unique opportunity. From the moment you step inside, you will be impressed by the attention to detail and the quality of construction. This is a must-see property for anyone looking for the best in class. Perfect for families, professionals, or investors seeking a valuable addition to their portfolio. Don't miss out on the chance to own this magnificent piece of real estate. Everything you need is right at your doorstep, making this an ideal choice for a modern family.";

        // Noida (20)
        for (int i = 1; i <= 20; i++) {
            properties.add(new Property("Noida Luxury Tower " + i, "Sector " + (100 + i) + ", Noida", "₹ " + (1 + i/10.0) + " Cr", 
                "https://images.unsplash.com/photo-1580587771525-78b9dba3b914", longDesc, "Apartment", "Sell", "3 BHK", "15 Floors"));
        }
        // Delhi (20)
        for (int i = 1; i <= 20; i++) {
            properties.add(new Property("Delhi Elite Residency " + i, "Kirti Nagar, Delhi", "₹ " + (50 + i * 5) + " L", 
                "https://images.unsplash.com/photo-1493809842364-78817add7ffb", longDesc, "Apartment", "Sell", "2 BHK", "4 Floors"));
        }
        // Mumbai (20)
        for (int i = 1; i <= 20; i++) {
            properties.add(new Property("Mumbai Sea View " + i, "Worli, Mumbai", "₹ " + (5 + i) + " Cr", 
                "https://images.unsplash.com/photo-1545324418-cc1a3fa10c00", longDesc, "Apartment", "Sell", "4 BHK", "50 Floors"));
        }
        // Gurgaon (20)
        for (int i = 1; i <= 20; i++) {
            properties.add(new Property("Gurgaon Garden Villa " + i, "Sector 27, Gurgaon", "₹ " + (2 + i/5.0) + " Cr", 
                "https://images.unsplash.com/photo-1570129477492-45c003edd2be", longDesc, "House", "Sell", "4 BHK", "2 Floors"));
        }
        // Others (20)
        for (int i = 1; i <= 20; i++) {
            properties.add(new Property("Premium Plot " + i, "Sector 20, Sirsa", "₹ " + (1 + i/10.0) + " Cr", 
                "https://images.unsplash.com/photo-1500382017468-9049fed747ef", longDesc, "Plot / Land", "Sell", "N/A", "0 Floors"));
        }
    }
}