package com.doanchuyennganh.eatio.entity.GoogleGeoCodingApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TungHo on 05/10/2017.
 */

public class ggGeocodingResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("results")
    public List<Address> address;

}
