package com.doanchuyennganh.eatio.api.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/20/2017.
 */

public class CreateLocationRequest implements Serializable {
    @SerializedName("token")
    public String token;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("city")
    public String city;
}
