package com.example.smartparking;

public class CompositeObject {

    String key,value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    CompositeObject()
    {
        value=null;
        key=null;
    }
}
