package com.doanchuyennganh.eatio.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public class Location implements Serializable{

    @SerializedName("id")
    public int id;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("city")
    public String city;

    @SerializedName("province")
    public String province;

    @SerializedName("address")
    public String fullAddress;

    @SerializedName("place_id")
    public String placeId;  // use for Google Place API

    @SerializedName("profile_id")
    public int profileId;

    //Foreign Key
    @SerializedName("fonda_id")
    public int fondaId;

    @Override
    public String toString(){
        return latitude + "," + longitude;
    }
}
