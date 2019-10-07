package com.example.smartparking;

public class AdminObject {

    float avgUsage,peakHr,lowHr, pricePerHour;
    String name, key;

    public void setKey(String key) {
        this.key = key;
    }

    public void setAvgUsage(float avgUsage) {
        this.avgUsage = avgUsage;
    }

    public void setLowHr(float lowHr) {
        this.lowHr = lowHr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeakHr(float peakHr) {
        this.peakHr = peakHr;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
    AdminObject()
    {
        avgUsage=peakHr=lowHr=pricePerHour=0;
        name=null ;
        key=null;

    }
}
