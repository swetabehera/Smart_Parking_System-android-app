package com.example.smartparking;
//user and admin profiles re delete admin or user account

public class UserObject {
    String name,key, startTime,currentLocn,endTime, favLocn, vehicleNo, payment;

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setFavLocn(String favLocn) {
        this.favLocn = favLocn;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getCurrentLocn() {
        return currentLocn;
    }

    public void setCurrentLocn(String currentLocn) {
        this.currentLocn = currentLocn;
    }

    public String getFavLocn() {
        return favLocn;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getPayment() {
        return payment;
    }

    UserObject()
    {
        name=null;
        key=null;
        currentLocn=null;
        startTime=null;
        endTime=null;
        favLocn=null;
        vehicleNo=null; payment=null;

    }
}

