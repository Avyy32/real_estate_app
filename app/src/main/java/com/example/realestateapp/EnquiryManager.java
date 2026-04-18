package com.example.realestateapp;

import java.util.ArrayList;
import java.util.List;

public class EnquiryManager {
    private static List<Enquiry> enquiries = new ArrayList<>();

    public static void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    public static List<Enquiry> getEnquiries() {
        return enquiries;
    }
}
