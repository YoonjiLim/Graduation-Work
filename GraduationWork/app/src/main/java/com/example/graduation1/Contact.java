package com.example.graduation1;

public class Contact {
    private String name;
    private String address;
    private String phoneNumber;
    private String area;

    public Contact(String area,String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.area=area;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getArea() {
        return area;
    }
}
