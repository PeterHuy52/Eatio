package com.doanchuyennganh.eatio.data.model;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/22/2017.
 */

public class LocationModel implements Serializable {

    public int id;

    public double longitude;

    public String latitude;

    public String city;

    private String fullAddress;

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
