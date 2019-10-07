package com.example.smartparking;

public class ParkingSlotObject {
    String key,name,location,adminId;
    double area;
    int capacity;//no.of vehicles

    public String getKey() {
        return key;
    }

    public double getArea() {
        return area;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    ParkingSlotObject()
    {
        name=key=adminId=location=null;
        capacity=0;
        area=0.0;
    }
}
