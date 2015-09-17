package com.akiniyalocts.dedo.realm;

import io.realm.RealmObject;

public class RealmString extends RealmObject {

    private String stringValue;

    public RealmString(){}

    public RealmString(String stringValue){
        this.stringValue =  stringValue;
    }


    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

}