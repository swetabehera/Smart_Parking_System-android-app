package com.example.smartparking;

public class VehicleObj {
    int noOfWheels;
    float dimension;//keep either 1
    String vehno, vehId,userId;//registration no.

    public void setDimension(float dimension) {
        this.dimension = dimension;
    }

    public void setNoOfWheels(int noOfWheels) {
        this.noOfWheels = noOfWheels;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setVehId(String vehId) {
        this.vehId = vehId;
    }

    public void setVehno(String vehno) {
        this.vehno = vehno;
    }
    VehicleObj()
    {
        noOfWheels=0;
        dimension=0;
        vehId=vehno=userId=null;
    }
}
