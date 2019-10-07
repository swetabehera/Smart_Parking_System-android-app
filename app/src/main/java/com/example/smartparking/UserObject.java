package com.example.smartparking;

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

