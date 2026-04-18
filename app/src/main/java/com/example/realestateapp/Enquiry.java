package com.example.realestateapp;

public class Enquiry {
    private String propertyName;
    private String userName;
    private String phone;
    private String message;

    public Enquiry(String propertyName, String userName, String phone, String message) {
        this.propertyName = propertyName;
        this.userName = userName;
        this.phone = phone;
        this.message = message;
    }

    public String getPropertyName() { return propertyName; }
    public String getUserName() { return userName; }
    public String getPhone() { return phone; }
    public String getMessage() { return message; }
}
