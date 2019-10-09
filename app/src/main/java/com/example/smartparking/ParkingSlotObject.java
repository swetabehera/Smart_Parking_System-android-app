
package com.example.smartparking;


public class ParkingSlotObject
{
    public  int capacity;
    public double area;
    public String key,adminId,location,nameOfArea;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNameOfArea() {
        return nameOfArea;
    }

    public void setNameOfArea(String nameOfArea) {
        this.nameOfArea = nameOfArea;
    }

    public ParkingSlotObject() {
    }

    public ParkingSlotObject(int capacity, double area, String key, String adminId, String location, String nameOfArea) {
        this.capacity = capacity;
        this.area = area;
        this.key = key;
        this.adminId = adminId;
        this.location = location;
        this.nameOfArea = nameOfArea;
    }
}