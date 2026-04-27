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
        // Diverse dummy data (20 properties)
        properties.add(new Property("Modern Family House", "Sector 27, Gurgaon", "₹ 2.45 Cr", 
            "https://images.unsplash.com/photo-1570129477492-45c003edd2be", "Beautiful house in Gurgaon with spacious garden.", "House", "Sell", "3 BHK", "2 Floors"));
        properties.add(new Property("Luxury Apartment", "Noida Sector 150", "₹ 1.50 Cr", 
            "https://images.unsplash.com/photo-1580587771525-78b9dba3b914", "Luxury living in Noida with modern amenities.", "Apartment", "Sell", "3 BHK", "10 Floors"));
        properties.add(new Property("Independent Floor", "Sector 20 Huda, Sirsa", "₹ 1.10 Cr", 
            "https://images.unsplash.com/photo-1512917774080-9991f1c4c750", "Prime location independent floor in Sirsa.", "Builder Floor", "Sell", "4 BHK", "3 Floors"));
        properties.add(new Property("Affordable Flat", "Kirti Nagar, Delhi", "₹ 55 L", 
            "https://images.unsplash.com/photo-1493809842364-78817add7ffb", "Perfect budget-friendly flat in the heart of Delhi.", "Apartment", "Sell", "2 BHK", "4 Floors"));
        properties.add(new Property("Spacious Villa", "Whitefield, Bangalore", "₹ 4.80 Cr", 
            "https://images.unsplash.com/photo-1613977257363-707ba9348227", "High-end villa with private pool.", "House", "Sell", "4 BHK", "2 Floors"));
        properties.add(new Property("Studio Apartment", "Cyber City, Gurgaon", "₹ 45,000", 
            "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267", "Modern studio apartment for working professionals.", "Apartment", "Rent", "1 RK", "15 Floors"));
        properties.add(new Property("Prime Plot", "Sector 19, Sirsa", "₹ 1.20 Cr", 
            "https://images.unsplash.com/photo-1500382017468-9049fed747ef", "Investment plot with huge potential.", "Plot / Land", "Sell", "N/A", "0 Floors"));
        properties.add(new Property("Commercial Shop", "Connaught Place, Delhi", "₹ 5.00 Cr", 
            "https://images.unsplash.com/photo-1582410330041-622749f6361c", "Prime commercial space in high-traffic area.", "Commercial", "Sell", "N/A", "1 Floors"));
        properties.add(new Property("Ready to Move House", "Preet Nagar, Sirsa", "₹ 1.10 Cr", 
            "https://images.unsplash.com/photo-1564013799919-ab600027ffc6", "Fully finished house ready for immediate possession.", "House", "Sell", "3 BHK", "2 Floors"));
        properties.add(new Property("Penthouse Suite", "Mumbai Worli", "₹ 8.50 Cr", 
            "https://images.unsplash.com/photo-1545324418-cc1a3fa10c00", "Ultra-luxury penthouse with panoramic sea view.", "Apartment", "Sell", "4 BHK", "40 Floors"));
        properties.add(new Property("Compact City Flat", "HSR Layout, Bangalore", "₹ 65 L", 
            "https://images.unsplash.com/photo-1502672260266-1c1ef2d93688", "Convenient city flat near IT parks.", "Apartment", "Sell", "2 BHK", "5 Floors"));
        properties.add(new Property("Sky Villa", "Golf Course Road, Gurgaon", "₹ 12.00 Cr", 
            "https://images.unsplash.com/photo-1512918766675-ed20c3522c1b", "The pinnacle of luxury living in Gurgaon.", "Apartment", "Sell", "5 BHK", "20 Floors"));
        properties.add(new Property("Historical Row House", "Banjara Hills, Hyderabad", "₹ 4.50 Cr", 
            "https://images.unsplash.com/photo-1513584684374-8bdb7489feef", "Classic row house with modern interiors.", "House", "Sell", "4 BHK", "3 Floors"));
        properties.add(new Property("Furnished Rental", "Noida Sector 62", "₹ 35,000", 
            "https://images.unsplash.com/photo-1536376074432-84426582960c", "Fully furnished apartment ready to rent.", "Apartment", "Rent", "2 BHK", "8 Floors"));
        properties.add(new Property("Modern Office", "Gachibowli, Hyderabad", "₹ 1.50 Cr", 
            "https://images.unsplash.com/photo-1497366216548-37526070297c", "State-of-the-art office space in IT hub.", "Commercial", "Sell", "N/A", "10 Floors"));
        properties.add(new Property("Garden House", "New Friends Colony, Delhi", "₹ 3.20 Cr", 
            "https://images.unsplash.com/photo-1512917774080-9991f1c4c750", "Beautiful home with a lush green garden.", "House", "Sell", "3 BHK", "2 Floors"));
        properties.add(new Property("High-Rise Rental", "Pune Magarpatta", "₹ 28,000", 
            "https://images.unsplash.com/photo-1502672260266-1c1ef2d93688", "Flat with great city views in Pune.", "Apartment", "Rent", "2 BHK", "12 Floors"));
        properties.add(new Property("Investment Land", "Goa Beachfront", "₹ 6.00 Cr", 
            "https://images.unsplash.com/photo-1500382017468-9049fed747ef", "Beachfront plot in prime Goa location.", "Plot / Land", "Sell", "N/A", "0 Floors"));
        properties.add(new Property("Executive Apartment", "Chennai OMR", "₹ 85 L", 
            "https://images.unsplash.com/photo-1545324418-cc1a3fa10c00", "Perfect apartment for tech professionals.", "Apartment", "Sell", "3 BHK", "8 Floors"));
        properties.add(new Property("Large Warehouse", "Bhiwandi, Mumbai", "₹ 2.00 Cr", 
            "https://images.unsplash.com/photo-1586528116311-ad8dd3c8310d", "Large storage capacity commercial warehouse.", "Commercial", "Sell", "N/A", "1 Floors"));
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
}