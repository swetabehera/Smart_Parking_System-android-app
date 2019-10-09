package com.example.smartparking;

public class AdminObject {

    float avgUsage,peakHr,lowHr, pricePerHour;
    String name, key;

    public float getAvgUsage() {
        return avgUsage;
    }

    public float getPeakHr() {
        return peakHr;
    }

    public float getLowHr() {
        return lowHr;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

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
        avgUsage=0;
        peakHr=0;
        lowHr=0;
        pricePerHour=0;
        name=null ;
        key=null;

    }
}
