package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public class LocationEntity implements Serializable{

    @SerializedName("id")
    public int id;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("latitude")
    public String latitude;

    @SerializedName("city")
    public String city;

    @SerializedName("address")
    public String fullAddress;

    @SerializedName("profile_id")
    public int profileId;

    //Foreign Key
    @SerializedName("fonda_id")
    public int fondaId;
}
